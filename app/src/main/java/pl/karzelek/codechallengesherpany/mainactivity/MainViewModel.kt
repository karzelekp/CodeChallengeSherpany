package pl.karzelek.codechallengesherpany.mainactivity

import androidx.lifecycle.ViewModel
import pl.karzelek.codechallengesherpany.db.ChallengeDatabase
import javax.inject.Inject

class MainViewModel @Inject constructor(database: ChallengeDatabase) : ViewModel() {

    val postsWithUsers = database.postDao().getAllPostsWithUsers()
}