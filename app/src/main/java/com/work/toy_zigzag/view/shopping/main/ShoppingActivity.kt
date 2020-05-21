package com.work.toy_zigzag.view.shopping.main

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.work.toy_zigzag.App
import com.work.toy_zigzag.R
import com.work.toy_zigzag.data.model.ShoppingDocumentsItem
import com.work.toy_zigzag.data.model.ShoppingItem
import com.work.toy_zigzag.view.shopping.ShoppingListener
import com.work.toy_zigzag.view.shopping.filter.ShoppingFilterFragment
import com.work.toy_zigzag.view.shopping.main.adapter.ShoppingAdapter
import com.work.toy_zigzag.view.shopping.main.presenter.ShoppingContract
import com.work.toy_zigzag.view.splash.SplashActivity
import kotlinx.android.synthetic.main.shopping_main.*
import org.koin.android.ext.android.get
import org.koin.core.parameter.parametersOf

class ShoppingActivity : AppCompatActivity(), View.OnClickListener, ShoppingContract.View,
    ShoppingListener {


    private lateinit var presenter: ShoppingContract.Presenter

    private val shoppingAdapter by lazy { ShoppingAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.shopping_main)
        presenter = get { parametersOf(this) }
        btn_filter.setOnClickListener(this)

        startView()
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_filter -> {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fl_container, ShoppingFilterFragment())
                    .addToBackStack(null)
                    .commit()
            }
        }
    }

    override fun getData(shoppingItem: ShoppingItem) {
        shoppingAdapter.clear()
        presenter.getShoppingItem(shoppingItem)
    }

    override fun showShoppingItem(updateDate: String, list: List<ShoppingDocumentsItem>) {
        tv_update_date.text =
            getString(R.string.shopping_main_date, updateDate)

        tv_select_filter.apply {
            text = App.prefs.selectFilter
            isVisible = App.prefs.selectFilter.isNotEmpty()
        }
        list.forEach { item ->
            shoppingAdapter.addData(item)
        }
    }

    private fun startView() {
        rv_shopping.run {
            layoutManager = LinearLayoutManager(this@ShoppingActivity)
            adapter = shoppingAdapter
        }

        presenter.getShoppingItem(intent.getParcelableExtra(SplashActivity.SHOPPING_ITEM))
    }


}