package pl.karzelek.codechallengesherpany.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import pl.karzelek.codechallengesherpany.entities.Album

@Dao
interface AlbumDao {
    @Query("SELECT * FROM albums")
    fun getAll(): LiveData<List<Album>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(albums: Collection<Album>)
}
