package com.work.toy_zigzag.view.shopping.main

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.work.toy_zigzag.App
import com.work.toy_zigzag.R
import com.work.toy_zigzag.data.model.ShoppingDocumentsItem
import com.work.toy_zigzag.data.model.ShoppingItem
import com.work.toy_zigzag.databinding.ShoppingMainBinding
import com.work.toy_zigzag.ext.replaceFragmentInActivity
import com.work.toy_zigzag.view.shopping.ShoppingListener
import com.work.toy_zigzag.view.shopping.filter.ShoppingFilterFragment
import com.work.toy_zigzag.view.shopping.main.adapter.ShoppingAdapter
import com.work.toy_zigzag.view.shopping.main.presenter.ShoppingContract
import com.work.toy_zigzag.view.splash.SplashActivity
import org.koin.android.ext.android.get
import org.koin.core.parameter.parametersOf

class ShoppingActivity : AppCompatActivity(), View.OnClickListener, ShoppingContract.View,
    ShoppingListener {


    private lateinit var presenter: ShoppingContract.Presenter
    private lateinit var binding: ShoppingMainBinding

    private val shoppingAdapter by lazy { ShoppingAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding =
            DataBindingUtil.setContentView(this, R.layout.shopping_main)

        presenter = get { parametersOf(this) }

        binding.btnFilter.setOnClickListener(this)

        startView()
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_filter -> {
                replaceFragmentInActivity(ShoppingFilterFragment(), R.id.fl_container)
            }
        }
    }

    override fun getData(shoppingItem: ShoppingItem) {
        shoppingAdapter.clear()
        presenter.getShoppingItem(shoppingItem)
    }

    override fun showShoppingItem(updateDate: String, list: List<ShoppingDocumentsItem>) {

        binding.updateDate =
            getString(R.string.shopping_main_date, updateDate)

        binding.tvSelectFilter.apply {
            text = App.prefs.selectFilter
            isVisible = App.prefs.selectFilter.isNotEmpty()
        }

        list.forEach { item ->
            shoppingAdapter.addData(item)
        }
    }

    private fun startView() {

        binding.rvShopping.run {
            layoutManager = LinearLayoutManager(this@ShoppingActivity)
            adapter = shoppingAdapter
        }

        val getShoppingItem =
            intent.getParcelableExtra<ShoppingItem>(SplashActivity.SHOPPING_ITEM)

        getShoppingItem?.let { shoppingItem ->
            presenter.getShoppingItem(shoppingItem)
        }
    }
}