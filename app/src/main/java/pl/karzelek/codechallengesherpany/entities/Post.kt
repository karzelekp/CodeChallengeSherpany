package pl.karzelek.codechallengesherpany.entities

import androidx.room.*
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
    ],
    indices = [
        Index("user_id")
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