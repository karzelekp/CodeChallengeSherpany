package pl.karzelek.codechallengesherpany.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import pl.karzelek.codechallengesherpany.entities.Photo

@Dao
interface PhotoDao {
    @Query("SELECT * FROM photos")
    fun getAll(): LiveData<List<Photo>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(posts: Collection<Photo>)
}