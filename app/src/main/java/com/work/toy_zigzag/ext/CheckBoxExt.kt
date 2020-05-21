package com.work.toy_zigzag.ext

import android.graphics.drawable.GradientDrawable
import android.widget.CheckBox
import androidx.core.content.ContextCompat
import com.work.toy_zigzag.App


fun CheckBox.changeBackground(checkBox: CheckBox, changeColor: Int) {
    val gradientDrawable =
        (checkBox.background as GradientDrawable).mutate()
    (gradientDrawable as GradientDrawable).setColor(
        ContextCompat.getColor(
            App.instance.context(),
            changeColor
        )
    )
}

fun CheckBox.changeText(checkBox: CheckBox, changeColor: Int) {
    checkBox.setTextColor(
        ContextCompat.getColor(
            App.instance.context(),
            changeColor
        )
    )
}
