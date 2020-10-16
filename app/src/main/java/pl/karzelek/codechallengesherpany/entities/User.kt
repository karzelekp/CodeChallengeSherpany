package pl.karzelek.codechallengesherpany.entities

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "users")
data class User(
    @PrimaryKey
    @ColumnInfo(name = "id")
    @SerializedName("id")
    val id: Int?,

    @ColumnInfo(name = "name")
    @SerializedName("name")
    val name: String?,

    @ColumnInfo(name = "user_name")
    @SerializedName("username")
    val username: String?,

    @ColumnInfo(name = "email")
    @SerializedName("email")
    val email: String?,

    @Embedded
    @SerializedName("address")
    val address: Address?,

    @ColumnInfo(name = "phone")
    @SerializedName("phone")
    val phone: String?,

    @ColumnInfo(name = "web_site")
    @SerializedName("website")
    val website: String?,

    @Embedded(prefix = "company_")
    @SerializedName("company")
    val company: Company?
)