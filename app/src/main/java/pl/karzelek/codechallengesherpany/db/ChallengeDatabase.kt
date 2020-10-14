package pl.karzelek.codechallengesherpany.db

import androidx.room.Database
import androidx.room.RoomDatabase
import pl.karzelek.codechallengesherpany.api.Post

@Database(entities = [Post::class], version = 1)
abstract class ChallengeDatabase : RoomDatabase() {
    abstract fun postDao(): PostDao

    companion object {
        const val NAME = "challenge.db"
    }
}
