package pl.karzelek.codechallengesherpany.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(
    tableName = "posts",
    foreignKeys = [
        ForeignKey(
            entity = User::class,
            parentColumns = ["id"],
            childColumns = ["user_id"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class Post(
    @SerializedName("id")
    @ColumnInfo(name = "id")
    @PrimaryKey
    val id: Int?,

    @SerializedName("userId")
    @ColumnInfo(name = "user_id")
    val userId: Int?,

    @SerializedName("title")
    @ColumnInfo(name = "title")
    val title: String?,

    @SerializedName("body")
    @ColumnInfo(name = "body")
    val body: String?
)