package com.work.toy_zigzag.network.room.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.work.toy_zigzag.network.room.entity.ShoppingDocuments

class ShoppingDocumentsConverter {
    @TypeConverter
    fun listToJson(value: List<ShoppingDocuments>?): String {
        return Gson().toJson(value)
    }

    @TypeConverter
    fun jsonToList(value: String): List<ShoppingDocuments>? {
        val objects =
            Gson().fromJson(
                value,
                Array<ShoppingDocuments>::class.java
            )
        return objects.toList()
    }
}