package pl.karzelek.codechallengesherpany.api

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity
data class Post(
    @PrimaryKey(autoGenerate = true)
    @Expose(serialize = false, deserialize = false)
    @ColumnInfo(name = "local_id")
    val localId: Int = 0,

    @SerializedName("userId")
    @ColumnInfo(name = "user_id")
    val userId: Int?,

    @SerializedName("id")
    @ColumnInfo(name = "server_id")
    val serverId: Int?,

    @SerializedName("title")
    @ColumnInfo(name = "title")
    val title: String?,

    @SerializedName("body")
    @ColumnInfo(name = "body")
    val body: String?
)