package pl.karzelek.codechallengesherpany

import android.database.sqlite.SQLiteException
import android.util.Log
import kotlinx.coroutines.*
import pl.karzelek.codechallengesherpany.api.Api
import pl.karzelek.codechallengesherpany.db.ChallengeDatabase
import pl.karzelek.codechallengesherpany.entities.Album
import pl.karzelek.codechallengesherpany.entities.Photo
import pl.karzelek.codechallengesherpany.entities.Post
import pl.karzelek.codechallengesherpany.entities.User
import java.net.UnknownHostException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Repository @Inject constructor(
    private val api: Api,
    private val database: ChallengeDatabase
) {

    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        when (throwable) {
            is UnknownHostException -> {
                Log.d(TAG, "network error")
            }
            is SQLiteException -> {
                Log.d(TAG, "database error")
                throwable.printStackTrace()
            }
            else -> {
                Log.d(TAG, "unknown error")
                throwable.printStackTrace()
            }
        }
    }

    fun onAppColdStarted() {
        fetchData()
    }

    private fun fetchData() {
        CoroutineScope(Dispatchers.IO + coroutineExceptionHandler).launch {
            val users = async { api.suspendUsers() }
            val posts = async { api.suspendPosts() }
            val albums = async { api.suspendAlbums() }
            val photos = async { api.suspendPhotos() }

            writeEntities(users.await(), posts.await(), albums.await(), photos.await())
        }
    }

    private suspend fun writeEntities(users: List<User>, posts: List<Post>, albums: List<Album>, photos: List<Photo>) =
        withContext(Dispatchers.IO) {
            database.runInTransaction {
                database.userDao().insertAll(users)
                database.postDao().insertAll(posts)
                database.albumDao().insertAll(albums)
                database.photoDao().insertAll(photos)
            }
        }
}

private const val TAG = "Repository"