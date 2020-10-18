package pl.karzelek.codechallengesherpany.db

import androidx.lifecycle.LiveData
import androidx.room.*
import pl.karzelek.codechallengesherpany.entities.User

@Dao
interface UserDao {
    @Query("SELECT * FROM users")
    fun getAll(): LiveData<List<User>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(users: Collection<User>)

    @Query("SELECT * FROM users where id = :userId")
    @Transaction
    suspend fun getUserWithAlbums(userId: Int): List<UserWithAlbums>
}