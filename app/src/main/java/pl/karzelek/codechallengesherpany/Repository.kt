package pl.karzelek.codechallengesherpany

import pl.karzelek.codechallengesherpany.api.Api
import pl.karzelek.codechallengesherpany.db.ChallengeDatabase
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Repository @Inject constructor(
    private val api: Api,
    private val database: ChallengeDatabase
) {

    fun onAppColdStarted() {
        fetchPosts()
    }

    private fun fetchPosts() {
        api.posts().subscribe {
            it.response()?.body()?.let { list ->
                database.postDao().insertAll(list)
            }
        }
    }
}