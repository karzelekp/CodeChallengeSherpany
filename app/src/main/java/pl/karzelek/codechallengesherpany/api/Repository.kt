package pl.karzelek.codechallengesherpany.api

import android.content.Context
import android.database.sqlite.SQLiteException
import android.util.Log
import androidx.annotation.StringRes
import kotlinx.coroutines.*
import pl.karzelek.codechallengesherpany.R
import pl.karzelek.codechallengesherpany.db.ChallengeDatabase
import pl.karzelek.codechallengesherpany.di.IoDispatcher
import pl.karzelek.codechallengesherpany.di.MainDispatcher
import pl.karzelek.codechallengesherpany.entities.Album
import pl.karzelek.codechallengesherpany.entities.Photo
import pl.karzelek.codechallengesherpany.entities.Post
import pl.karzelek.codechallengesherpany.entities.User
import pl.karzelek.codechallengesherpany.extensions.showToast
import java.net.UnknownHostException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Repository @Inject constructor(
    private val api: Api,
    private val database: ChallengeDatabase,
    private val context: Context,
    @IoDispatcher
    private val ioDispatcher: CoroutineDispatcher,
    @MainDispatcher
    private val mainDispatcher: CoroutineDispatcher
) {

    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        when (throwable) {
            is UnknownHostException -> {
                Log.d(TAG, "network error")
                showToast(R.string.internet_error)
            }
            is SQLiteException -> {
                Log.e(TAG, "database error")
                throwable.printStackTrace()
                showToast(R.string.database_error)
            }
            else -> {
                Log.e(TAG, "an unknown error occurred")
                throwable.printStackTrace()
                showToast(R.string.unknown_error)
            }
        }
    }

    fun onAppColdStarted() {
        fetchData()
    }

    private fun fetchData() {
        CoroutineScope(ioDispatcher + coroutineExceptionHandler).launch {
            val users = async { api.suspendUsers() }
            val posts = async { api.suspendPosts() }
            val albums = async { api.suspendAlbums() }
            val photos = async { api.suspendPhotos() }

            writeEntities(users.await(), posts.await(), albums.await(), photos.await())
        }
    }

    private suspend fun writeEntities(users: List<User>, posts: List<Post>, albums: List<Album>, photos: List<Photo>) =
        withContext(ioDispatcher) {
            database.runInTransaction {
                database.userDao().insertAll(users)
                database.postDao().insertAll(posts)
                database.albumDao().insertAll(albums)
                database.photoDao().insertAll(photos)
            }
        }

    private fun showToast(@StringRes databaseError: Int) {
        CoroutineScope(mainDispatcher).launch {
            context.showToast(databaseError)
        }
    }

    companion object {
        private const val TAG = "Repository"
    }
}