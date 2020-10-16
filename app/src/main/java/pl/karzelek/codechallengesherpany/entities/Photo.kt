package pl.karzelek.codechallengesherpany.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "photos")
data class Photo(
    @PrimaryKey
    @ColumnInfo(name = "id")
    @SerializedName("id")
    val id: Int?,

    @ColumnInfo(name = "album_id")
    @SerializedName("albumId")
    val albumId: Int?,

    @ColumnInfo(name = "title")
    @SerializedName("title")
    val title: String?,

    @ColumnInfo(name = "url")
    @SerializedName("url")
    val url: String?,

    @ColumnInfo(name = "thumbnail_url")
    @SerializedName("thumbnailUrl")
    val thumbnailUrl: String?
)