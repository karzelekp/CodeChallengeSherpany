package pl.karzelek.codechallengesherpany.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import pl.karzelek.codechallengesherpany.api.Post

@Dao
interface PostDao {
    @Query("SELECT * FROM post")
    fun getAll(): LiveData<List<Post>>

    @Insert
    fun insertAll(posts: Collection<Post>)
}