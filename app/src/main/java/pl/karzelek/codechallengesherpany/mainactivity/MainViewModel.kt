package pl.karzelek.codechallengesherpany.mainactivity

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import pl.karzelek.codechallengesherpany.db.ChallengeDatabase
import pl.karzelek.codechallengesherpany.db.PostWithUser
import javax.inject.Inject

class MainViewModel @Inject constructor(private val database: ChallengeDatabase) : ViewModel() {

    val postsWithUsers = database.postDao().getAllPostsWithUsers()

    fun onDeleteClicked(postWithUser: PostWithUser) {
        Log.d(TAG, "deleting post with id: ${postWithUser.post.id}")
        viewModelScope.launch {
            database.postDao().delete(postWithUser.post)
        }
    }
}

private const val TAG = "MainViewModel"