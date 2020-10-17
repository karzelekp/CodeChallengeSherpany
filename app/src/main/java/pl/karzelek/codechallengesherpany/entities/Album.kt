package pl.karzelek.codechallengesherpany.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(
    tableName = "albums",
    foreignKeys = [
        ForeignKey(
            entity = User::class,
            parentColumns = ["id"],
            childColumns = ["user_id"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [
        androidx.room.Index("user_id")
    ]
)
data class Album(
    @PrimaryKey
    @ColumnInfo(name = "id")
    @SerializedName("id")
    val id: Int?,

    @SerializedName("userId")
    @ColumnInfo(name = "user_id")
    val userId: Int?,

    @SerializedName("title")
    @ColumnInfo(name = "title")
    val title: String?
)