package pl.karzelek.codechallengesherpany.entities

import androidx.room.ColumnInfo
import com.google.gson.annotations.SerializedName

data class Company(
    @ColumnInfo(name = "name")
    @SerializedName("name")
    val name: String?,

    @ColumnInfo(name = "catch_phrase")
    @SerializedName("catchPhrase")
    val catchPhrase: String?,

    @ColumnInfo(name = "bs")
    @SerializedName("bs")
    val bs: String?,
)