package com.work.toy_zigzag.network.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.work.toy_zigzag.network.room.entity.ShoppingEntity


@Dao
interface ShoppingDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun registerShoppingList(shoppingEntity: ShoppingEntity): Long

    @Query("SELECT * FROM shopping")
    fun getAll(): ShoppingEntity

}