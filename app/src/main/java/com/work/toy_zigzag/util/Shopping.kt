package com.work.toy_zigzag.util

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.widget.TextView
import com.work.toy_zigzag.App
import com.work.toy_zigzag.R
import com.work.toy_zigzag.data.model.ShoppingDocumentsItem
import com.work.toy_zigzag.data.model.ShoppingItem
import com.work.toy_zigzag.enums.State


object Shopping {


    private const val EMPTY_STRING = ""

    fun getImage(url: String): String {

        val splitUrl = url.split(".")

        return if (splitUrl[0].contains("www")) {
            convertUrl(splitUrl[1])
        } else {
            convertUrl(splitUrl[0].split("//")[1])
        }
    }

    private fun convertUrl(url: String): String =
        "https://cf.shop.s.zigzag.kr/images/$url.jpg"

    fun getAgeGroup(groupList: List<Int>): String {

        var ageGroup = EMPTY_STRING

        val ageList =
            App.instance.resources.getStringArray(R.array.ageGroup).toList()

        if (ageList.size != groupList.size)
            return ageGroup

        val toMapAgeList =
            mutableMapOf<String, Int>()

        groupList.forEachIndexed { index, i ->
            toMapAgeList[ageList[index]] = i
        }

        val containAge =
            toMapAgeList.filter { it.value == State.EXIST.value }.map { it.key }

        duplicateAge(containAge).sorted().let { list ->
            list.forEachIndexed { index, age ->
                ageGroup += age
                if (index != list.lastIndex)
                    ageGroup += " "

            }
        }

        if (containAge.size == ageList.size || ageGroup == "10대 20대 30대")
            return "모두"

        return ageGroup
    }


    private fun duplicateAge(ageList: List<String>): List<String> {
        val toDuplicateAge =
            mutableSetOf<String>()

        ageList.forEach { age ->
            when {
                age.contains("20대") -> toDuplicateAge.add("20대")
                age.contains("30대") -> toDuplicateAge.add("30대")
                else -> toDuplicateAge.add(age)
            }
        }
        return toDuplicateAge.toList()
    }


    fun saveStyleSort(shoppingItem: ShoppingItem) {
        val toSortList =
            shoppingItem.list.filter { it.sort != EMPTY_STRING }.map { it.sort }.distinct()

        var duplicationSort = EMPTY_STRING

        toSortList.forEachIndexed { index, sort ->
            duplicationSort += sort
            if (index != toSortList.lastIndex)
                duplicationSort += ","
        }

        App.prefs.styleList = duplicationSort

    }

    fun getStyleList(): List<String> =
        App.prefs.styleList.split(",").toList().distinct()


    fun getCheckList(item: Map<String, Int>): List<String> =
        item.filter { it.value == State.EXIST.value }.map { it.key }


    private fun sortContainAgeGroup(
        shoppingDocumentsItemList: List<ShoppingDocumentsItem>,
        ageGroup: List<Int>
    ): List<ShoppingDocumentsItem> {

        val toContainAge =
            mutableSetOf<ShoppingDocumentsItem>()

        shoppingDocumentsItemList.forEach { documentsItem ->
            ageGroup.forEachIndexed { index, i ->
                if (i == State.EXIST.value && documentsItem.ageGroup[index] == i) {
                    toContainAge.add(documentsItem)
                }
            }
        }
        return toContainAge.toList()
    }

    private fun sortContainStyle(
        shoppingDocumentsItemList: List<ShoppingDocumentsItem>,
        styleList: List<String>
    ): List<ShoppingDocumentsItem> {
        return if (styleList.isNotEmpty()) {
            val toContainStyle =
                mutableSetOf<ShoppingDocumentsItem>()

            styleList.forEach { style ->
                shoppingDocumentsItemList.forEach { documentsItem ->
                    if (documentsItem.sort.contains(style))
                        toContainStyle.add(documentsItem)
                }
            }
            toContainStyle.toList()
        } else {
            shoppingDocumentsItemList
        }
    }


    fun getCheckShoppingItem(
        shoppingItem: ShoppingItem,
        ageGroup: List<Int>,
        styleList: List<String>
    ): ShoppingItem {

        return if (ageGroup.sum() == State.EMPTY.value) {
            val toCheckList =
                sortContainStyle(
                    shoppingItem.list,
                    styleList
                )
            ShoppingItem(toCheckList, shoppingItem.week)
        } else {
            val toCheckList =
                sortContainStyle(
                    sortContainAgeGroup(
                        shoppingItem.list,
                        ageGroup
                    ),
                    styleList
                )
            ShoppingItem(toCheckList, shoppingItem.week)
        }
    }

    fun getStyle(sort: String?, style1: TextView, style2: TextView) {
        sort?.let {
            if (sort.contains(",")) {
                val list = sort.split(",")
                style1.apply {
                    text = list[0]
                    setStyleBackgroundColor(list[0], style1, State.CORNER_LEFT.value)
                }
                style2.apply {
                    text = list[1]
                    setStyleBackgroundColor(list[1], style2, State.CORNER_RIGHT.value)
                }
            } else {
                style1.apply {
                    text = sort
                    setStyleBackgroundColor(sort, style1, State.CORNER_ROUND.value)
                }
            }
        }
    }

    fun saveSelectFilter(checkAgeList: List<String>, checkStyleList: List<String>) {
        var ageList = EMPTY_STRING
        var styleList = EMPTY_STRING
        var saveSelectFilter = EMPTY_STRING

        checkAgeList.forEachIndexed { index, s ->
            ageList += s
            if (index != checkAgeList.lastIndex)
                ageList += ","
        }
        checkStyleList.forEachIndexed { index, s ->
            styleList += s
            if (index != checkStyleList.lastIndex)
                styleList += ","
        }
        when {
            ageList.isNotEmpty() && styleList.isNotEmpty() -> {
                saveSelectFilter = "$ageList / $styleList"
            }
            ageList.isEmpty() && styleList.isNotEmpty() -> {
                saveSelectFilter = styleList
            }
            ageList.isNotEmpty() && styleList.isEmpty() -> {
                saveSelectFilter = ageList
            }
        }
        App.prefs.selectFilter = saveSelectFilter
    }


    @SuppressLint("ResourceAsColor")
    private fun setStyleBackgroundColor(style: String, textView: TextView, cornerState: Int) {

        var cornerList = floatArrayOf()
        val gradientDrawable = GradientDrawable()

        when (cornerState) {
            State.CORNER_LEFT.value -> {
                cornerList = floatArrayOf(10f, 10f, 0f, 0f, 0f, 0f, 10f, 10f)
            }
            State.CORNER_RIGHT.value -> {
                cornerList = floatArrayOf(0f, 0f, 10f, 10f, 10f, 10f, 0f, 0f)
            }
            State.CORNER_ROUND.value -> {
                cornerList = floatArrayOf(10f, 10f, 10f, 10f, 10f, 10f, 10f, 10f)
            }
        }

        gradientDrawable.apply {
            setColor(
                Color.parseColor(
                    App.instance.context().resources.getStringArray(R.array.colorStyle)
                            [getStyleList().indexOf(style)]
                )
            )
            cornerRadii = cornerList
            setStroke(State.CORNER_STRIKE_WIDTH.value, R.color.colorLeanWhite)
            shape = GradientDrawable.RECTANGLE
        }

        textView.background = gradientDrawable
    }
}