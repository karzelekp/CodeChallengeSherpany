package pl.karzelek.codechallengesherpany.entities

import androidx.room.ColumnInfo
import com.google.gson.annotations.SerializedName

data class Geo(
    @ColumnInfo(name = "latitude")
    @SerializedName("lat")
    val latitude: String?,

    @ColumnInfo(name = "longitude")
    @SerializedName("lng")
    val longitude: String?
)