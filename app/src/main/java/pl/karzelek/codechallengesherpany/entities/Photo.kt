package pl.karzelek.codechallengesherpany.entities

import androidx.room.*
import com.google.gson.annotations.SerializedName

@ForeignKey(entity = Album::class, parentColumns = ["id"], childColumns = ["album_id"], onDelete = ForeignKey.CASCADE)
@Entity(
    tableName = "photos",
    foreignKeys = [
        ForeignKey(
            entity = Album::class,
            parentColumns = ["id"],
            childColumns = ["album_id"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [
        Index("album_id")
    ]
)
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