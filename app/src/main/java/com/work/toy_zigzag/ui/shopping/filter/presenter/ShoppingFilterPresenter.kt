package com.work.toy_zigzag.ui.shopping.filter.presenter

import com.work.toy_zigzag.App
import com.work.toy_zigzag.R
import com.work.toy_zigzag.data.repository.ShoppingRepository
import com.work.toy_zigzag.enums.Sort
import com.work.toy_zigzag.util.Shopping

class ShoppingFilterPresenter(
    private val shoppingFilterView: ShoppingFilterContract.View,
    private val shoppingRepository: ShoppingRepository
) : ShoppingFilterContract.Presenter {

    override fun checkSelectFilter(
        sort: Sort
    ) {
        val itemMapList = mutableListOf<Pair<String, Int>>()
        val isCheckAgeMap = mutableMapOf<String, Int>()
        val resource = App.instance.context().resources

        when (sort) {
            Sort.AGE -> {
                resource.getStringArray(R.array.ageGroup).forEach { ageGroupItem ->
                    var count = 0
                    Shopping.getAgeListBySelectFilter(App.prefs.selectFilter)
                        .forEach { selectItem ->
                            if (ageGroupItem == selectItem) {
                                count += 1
                            }
                        }
                    itemMapList.add(Pair(ageGroupItem, count))
                    isCheckAgeMap[ageGroupItem] = count
                }
                shoppingFilterView.showSelectFilterList(SORT_AGE, itemMapList, isCheckAgeMap)
            }
            Sort.STYLE -> {
                Shopping.getStyleList().forEach { styleItem ->
                    var count = 0
                    Shopping.getStyleListBySelectFilter(App.prefs.selectFilter)
                        .forEach { selectItem ->
                            if (styleItem == selectItem) {
                                count += 1
                            }
                        }
                    itemMapList.add(Pair(styleItem, count))
                    isCheckAgeMap[styleItem] = count
                }
                shoppingFilterView.showSelectFilterList(SORT_STYLE, itemMapList, isCheckAgeMap)
            }
        }
    }

    override fun getItem(ageGroup: List<Int>, styleList: List<String>) {
        shoppingRepository.getAll(
            onSuccess = { shoppingEntity ->
                val toShoppingItem =
                    shoppingEntity.toShoppingItem()

                shoppingFilterView.showItem(
                    Shopping.getCheckShoppingItem(
                        toShoppingItem,
                        ageGroup,
                        styleList
                    )
                )
            },
            onFailure = {

            }
        )
    }


    companion object {

        const val SORT_AGE = 0
        const val SORT_STYLE = 1

    }
}