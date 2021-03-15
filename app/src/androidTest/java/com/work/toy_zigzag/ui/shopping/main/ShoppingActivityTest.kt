package com.work.toy_zigzag.ui.shopping.main

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.work.toy_zigzag.R
import com.work.toy_zigzag.network.room.dao.ShoppingDao
import com.work.toy_zigzag.network.room.database.ShoppingDatabase
import com.work.toy_zigzag.network.room.entity.ShoppingEntity
import com.work.toy_zigzag.util.ConvertJson
import org.hamcrest.CoreMatchers.containsString
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.lang.Thread.sleep

@RunWith(AndroidJUnit4::class)
class ShoppingActivityTest {

    companion object {
        private const val FILE_NAME = "shop_list.json"
    }

    private lateinit var shoppingDatabase: ShoppingDatabase

    private lateinit var shoppingDao: ShoppingDao

    @Before
    fun init() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        shoppingDatabase = Room.inMemoryDatabaseBuilder(
            context,
            ShoppingDatabase::class.java
        ).build()

        shoppingDao = shoppingDatabase.shoppingListDao()

        registerShoppingData()
    }

    @After
    fun tearDown() {
        shoppingDatabase.close()
    }

    @Test
    fun should_show_list_week_when_start_main() {

        ActivityScenario.launch(ShoppingActivity::class.java)

        sleep(1000)

        onView(withId(R.id.rv_shopping)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_update_date)).check(matches(isDisplayed()))

    }

    @Test
    fun should_correct_week_when_start_main() {

        ActivityScenario.launch(ShoppingActivity::class.java)
        sleep(1500)

        val savedWeekData = shoppingDatabase.shoppingListDao().getAll().week

        onView(withId(R.id.tv_update_date)).check(matches(withText(containsString(savedWeekData))))

    }


    private fun registerShoppingData() {
        val getShoppingResponse =
            ConvertJson.getShoppingList(FILE_NAME)

        val toShoppingDocuments =
            getShoppingResponse.list.map { it.toShoppingDocument() }

        val shoppingEntity =
            ShoppingEntity(
                week = getShoppingResponse.week,
                list = toShoppingDocuments
            )

        shoppingDatabase.shoppingListDao()
            .registerShoppingList(shoppingEntity)
    }
}