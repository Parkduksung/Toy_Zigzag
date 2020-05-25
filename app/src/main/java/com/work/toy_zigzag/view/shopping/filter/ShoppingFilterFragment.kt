package com.work.toy_zigzag.view.shopping.filter

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.work.toy_zigzag.App
import com.work.toy_zigzag.R
import com.work.toy_zigzag.data.model.ShoppingItem
import com.work.toy_zigzag.enums.Sort
import com.work.toy_zigzag.enums.State
import com.work.toy_zigzag.util.Decoration
import com.work.toy_zigzag.util.Shopping
import com.work.toy_zigzag.util.Shopping.getCheckList
import com.work.toy_zigzag.util.Shopping.getStyleList
import com.work.toy_zigzag.view.shopping.ShoppingListener
import com.work.toy_zigzag.view.shopping.filter.adapter.AgeAdapter
import com.work.toy_zigzag.view.shopping.filter.adapter.StyleAdapter
import com.work.toy_zigzag.view.shopping.filter.adapter.listener.AdapterListener
import com.work.toy_zigzag.view.shopping.filter.presenter.ShoppingFilterContract
import kotlinx.android.synthetic.main.shopping_filter.*
import org.koin.android.ext.android.get
import org.koin.core.parameter.parametersOf

class ShoppingFilterFragment : Fragment(), View.OnClickListener, AdapterListener,
    ShoppingFilterContract.View {

    private lateinit var presenter: ShoppingFilterContract.Presenter

    private val ageAdapter by lazy { AgeAdapter(this) }
    private val styleAdapter by lazy { StyleAdapter(this) }

    private val ageMapList by lazy { mutableListOf<Pair<String, Int>>() }
    private val styleMapList by lazy { mutableListOf<Pair<String, Int>>() }

    private val isCheckAgeMap by lazy { mutableMapOf<String, Int>() }
    private val isCheckStyleMap by lazy { mutableMapOf<String, Int>() }

    private lateinit var shoppingListener: ShoppingListener

    override fun onAttach(context: Context) {
        super.onAttach(context)

        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                fragmentManager?.popBackStack()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(this, callback)

        (activity as? ShoppingListener)?.let {
            shoppingListener = it
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.shopping_filter, container, false).apply {
            setBackgroundColor(
                ContextCompat.getColor(
                    App.instance.context(),
                    R.color.colorWhite
                )
            )
            setOnTouchListener { _, _ ->
                true
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter = get { parametersOf(this) }

        iv_close.setOnClickListener(this)
        iv_refresh.setOnClickListener(this)
        tv_select_ok.setOnClickListener(this)

        startView()
    }


    override fun showItem(shoppingItem: ShoppingItem) {
        if (::shoppingListener.isInitialized) {
            shoppingListener.getData(shoppingItem)
            fragmentManager?.popBackStack()
        }
    }

    override fun onClick(v: View?) {
        when (v?.id) {

            R.id.iv_close -> {
                fragmentManager?.popBackStack()
            }

            R.id.iv_refresh -> {
                clear()
            }

            R.id.tv_select_ok -> {
                Shopping.saveSelectFilter(
                    isCheckAgeMap.filter { it.value == 1 }.map { it.key },
                    getCheckList(isCheckStyleMap)
                )
                presenter.getItem(
                    isCheckAgeMap.map { it.value },
                    getCheckList(isCheckStyleMap)
                )
            }
        }
    }

    private fun clear() {
        ageAdapter.clear()
        styleAdapter.clear()
        isCheckAgeMap.clear()
        isCheckStyleMap.clear()
    }

    override fun getItemState(sort: Sort, state: Pair<String, Int>) {
        when (sort) {
            Sort.AGE -> {
                isCheckAgeMap[state.first] = state.second
            }
            Sort.STYLE -> {
                isCheckStyleMap[state.first] = state.second
            }
        }
    }

    private fun startView() {

        resources.getStringArray(R.array.ageGroup).toList().forEach {
            ageMapList.add(Pair(it, State.UNCHECK.value))
            isCheckAgeMap[it] = State.UNCHECK.value
        }

        getStyleList().forEach {
            styleMapList.add(Pair(it, State.UNCHECK.value))
            isCheckStyleMap[it] = State.UNCHECK.value
        }

        rv_age.run {
            adapter = ageAdapter
            layoutManager = GridLayoutManager(context, AGE_SPAN_COUNT)
            addItemDecoration(Decoration(10, 10, 20, 20))
            ageAdapter.addAllData(ageMapList)
        }

        rv_style.run {
            adapter = styleAdapter
            layoutManager = GridLayoutManager(context, STYLE_SPAN_COUNT)
            addItemDecoration(Decoration(10, 10, 20, 20))
            styleAdapter.addAllData(styleMapList)
        }
    }

    companion object {

        const val TAG = "ShoppingFilterFragment"

        private const val STYLE_SPAN_COUNT = 3
        private const val AGE_SPAN_COUNT = 4
    }
}