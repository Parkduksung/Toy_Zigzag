package com.work.toy_zigzag.util

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class Decoration(var left: Int, var right: Int, var top: Int, var bottom: Int) :
    RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        outRect.also {
            it.left = left
            it.right = right
            it.top = top
            it.bottom = bottom
        }
    }
}