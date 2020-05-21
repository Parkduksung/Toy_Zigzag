package com.work.toy_zigzag.network.room.entity

import androidx.room.ColumnInfo
import com.work.toy_zigzag.data.model.ShoppingDocumentsItem

data class ShoppingDocuments(
    @ColumnInfo(name = "A")
    val ageGroup: List<Int>,
    @ColumnInfo(name = "n")
    val name: String,
    @ColumnInfo(name = "S")
    val sort: String,
    @ColumnInfo(name = "u")
    val url: String,
    @ColumnInfo(name = "0")
    val likeCount: Int
) {
    fun toShoppingDocumentsItem(): ShoppingDocumentsItem =
        ShoppingDocumentsItem(
            ageGroup,
            name,
            sort,
            url,
            likeCount
        )
}