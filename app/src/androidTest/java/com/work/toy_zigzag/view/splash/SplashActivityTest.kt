package com.work.toy_zigzag.view.splash

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.work.toy_zigzag.R
import com.work.toy_zigzag.network.room.dao.ShoppingDao
import com.work.toy_zigzag.network.room.database.ShoppingDatabase
import com.work.toy_zigzag.network.room.entity.ShoppingEntity
import com.work.toy_zigzag.util.ConvertJson
import junit.framework.Assert.assertNotNull
import junit.framework.Assert.assertNull
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class SplashActivityTest {

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
    }

    @After
    fun tearDown() {
        shoppingDatabase.close()
    }

    @Test
    fun should_show_title_when_launch_app() {

        ActivityScenario.launch(SplashActivity::class.java)

        Espresso.onView(ViewMatchers.withText(R.string.splash_main))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }


    @Test
    fun should_show_wifi_when_launch_app() {

        ActivityScenario.launch(SplashActivity::class.java)

        Espresso.onView(ViewMatchers.withText(R.string.splash_recommend_wifi))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun should_add_data_when_launch_app() {

        ActivityScenario.launch(SplashActivity::class.java)

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

        assertNotNull(shoppingDao.getAll())
    }

    @Test
    fun should_not_add_data_when_launch_app_and_not_add() {
        ActivityScenario.launch(SplashActivity::class.java)
        assertNull(shoppingDao.getAll())
    }


}