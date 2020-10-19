package pl.karzelek.codechallengesherpany.mainactivity

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import pl.karzelek.codechallengesherpany.db.ChallengeDatabase
import pl.karzelek.codechallengesherpany.db.PostWithUser
import pl.karzelek.codechallengesherpany.detailview.DetailViewState
import javax.inject.Inject

class MainViewModel @Inject constructor(private val database: ChallengeDatabase) : ViewModel() {

    val postsWithUsers = database.postDao().getAllPostsWithUsers()

    private val _detailViewState = MutableLiveData(DetailViewState())
    val detailViewState: LiveData<DetailViewState> = _detailViewState

    fun onDeleteClicked(postWithUser: PostWithUser) {
        Log.d(TAG, "deleting post with id: ${postWithUser.post.id}")
        viewModelScope.launch {
            database.postDao().delete(postWithUser.post)
        }
    }

    private var getUserWithAlbumsJob: Job? = null

    fun onPostSelected(postWithUser: PostWithUser) {
        Log.d(TAG, "selected the post with id: ${postWithUser.post.id}")
        getUserWithAlbumsJob?.cancel()
        val userId = postWithUser.user.id
        if (userId != null) {
            _detailViewState.value = DetailViewState(true)
            getUserWithAlbumsJob = viewModelScope.launch {
                val albums = database.albumDao().getAlbumsForUser(userId)
                _detailViewState.value = DetailViewState(false, postWithUser.post, albums)
            }
        }
    }
}

private const val TAG = "MainViewModel"