package pl.karzelek.codechallengesherpany.entities

import androidx.room.ColumnInfo
import androidx.room.Embedded
import com.google.gson.annotations.SerializedName

data class Address(
    @ColumnInfo(name = "street")
    @SerializedName("street")
    val street: String?,

    @ColumnInfo(name = "suite")
    @SerializedName("suite")
    val suite: String?,

    @ColumnInfo(name = "city")
    @SerializedName("city")
    val city: String?,

    @ColumnInfo(name = "zip_code")
    @SerializedName("zipcode")
    val zipCode: String?,

    @Embedded
    @SerializedName("geo")
    val geo: Geo?
)