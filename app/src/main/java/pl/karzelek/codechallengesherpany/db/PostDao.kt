package pl.karzelek.codechallengesherpany.db

import androidx.lifecycle.LiveData
import androidx.room.*
import pl.karzelek.codechallengesherpany.entities.Post

@Dao
interface PostDao {
    @Query("SELECT * FROM posts")
    fun getAll(): LiveData<List<Post>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(posts: Collection<Post>)

    @Query("SELECT * FROM posts")
    @Transaction
    fun getAllPostsWithUsers(): LiveData<List<PostWithUser>>
}