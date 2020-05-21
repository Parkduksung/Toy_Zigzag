package com.work.toy_zigzag.network.model


import com.google.gson.annotations.SerializedName
import com.work.toy_zigzag.network.room.entity.ShoppingDocuments

data class ShoppingDocumentsResponse(
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
) {
    fun toShoppingDocument(): ShoppingDocuments =
        ShoppingDocuments(
            ageGroup, name, sort, url, likeCount
        )

}