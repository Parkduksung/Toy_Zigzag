package com.work.toy_zigzag.util

import com.google.gson.Gson
import com.google.gson.JsonObject
import com.work.toy_zigzag.App
import com.work.toy_zigzag.network.model.ShoppingResponse

object ConvertJson {
    fun getShoppingList(fileName: String): ShoppingResponse {
        val assertManager =
            App.instance.context().assets
        val inputStream =
            assertManager.open(fileName)
        val readFile =
            inputStream.bufferedReader().use { it.readText() }
        val toJsonObject =
            Gson().fromJson(readFile, JsonObject::class.java)
        return Gson().fromJson(toJsonObject, ShoppingResponse::class.java)
    }
}
