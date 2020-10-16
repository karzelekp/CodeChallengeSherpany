package pl.karzelek.codechallengesherpany.db

import androidx.room.Database
import androidx.room.RoomDatabase
import pl.karzelek.codechallengesherpany.entities.Album
import pl.karzelek.codechallengesherpany.entities.Photo
import pl.karzelek.codechallengesherpany.entities.Post
import pl.karzelek.codechallengesherpany.entities.User

@Database(entities = [Post::class, Album::class, User::class, Photo::class], version = 1)
abstract class ChallengeDatabase : RoomDatabase() {

    abstract fun postDao(): PostDao
    abstract fun albumDao(): AlbumDao
    abstract fun userDao(): UserDao
    abstract fun photoDao(): PhotoDao

    companion object {
        const val NAME = "challenge.db"
    }
}
