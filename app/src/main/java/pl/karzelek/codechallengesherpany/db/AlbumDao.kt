package pl.karzelek.codechallengesherpany.db

import androidx.lifecycle.LiveData
import androidx.room.*
import pl.karzelek.codechallengesherpany.entities.Album

@Dao
interface AlbumDao {
    @Query("SELECT * FROM albums")
    fun getAll(): LiveData<List<Album>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(albums: Collection<Album>)

    @Query("SELECT * FROM albums where user_id = :userId")
    @Transaction
    suspend fun getAlbumsForUser(userId: Int): List<AlbumWithPhotos>
}
