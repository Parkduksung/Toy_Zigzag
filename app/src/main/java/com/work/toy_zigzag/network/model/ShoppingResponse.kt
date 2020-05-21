package com.work.toy_zigzag.network.model


import com.google.gson.annotations.SerializedName

data class ShoppingResponse(
    @SerializedName("list")
    val list: List<ShoppingDocumentsResponse>,
    @SerializedName("week")
    val week: String
)