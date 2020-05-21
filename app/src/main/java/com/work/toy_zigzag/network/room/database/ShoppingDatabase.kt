package com.work.toy_zigzag.network.room.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.work.toy_zigzag.network.room.converter.ShoppingDocumentsConverter
import com.work.toy_zigzag.network.room.dao.ShoppingDao
import com.work.toy_zigzag.network.room.entity.ShoppingEntity

@Database(entities = [ShoppingEntity::class], version = 1)
@TypeConverters(ShoppingDocumentsConverter::class)
abstract class ShoppingDatabase : RoomDatabase() {
    abstract fun shoppingListDao(): ShoppingDao
}