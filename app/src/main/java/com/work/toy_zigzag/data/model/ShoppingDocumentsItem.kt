package com.work.toy_zigzag.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ShoppingDocumentsItem(
    @SerializedName("A")
    val ageGroup: List<Int>,
    @SerializedName("n")
    val name: String,
    @SerializedName("S")
    val sort: String,
    @SerializedName("u")
    val url: String,
    @SerializedName("0")
    val likeCount: Int
) : Parcelable