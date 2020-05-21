package com.work.toy_zigzag.network.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.work.toy_zigzag.data.model.ShoppingItem
import com.work.toy_zigzag.network.room.converter.ShoppingDocumentsConverter

@Entity(tableName = "shopping")
data class ShoppingEntity(
    @PrimaryKey(autoGenerate = true)
    val listNum: Int = 0,
    @ColumnInfo(name = "week")
    val week: String,
    @TypeConverters(ShoppingDocumentsConverter::class)
    val list: List<ShoppingDocuments>
) {
    fun toShoppingItem(): ShoppingItem =
        ShoppingItem(
            list.map { it.toShoppingDocumentsItem() },
            week
        )
}
