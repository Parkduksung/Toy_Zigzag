package com.work.toy_zigzag.ext

import android.content.Context
import android.widget.Toast

fun Context.showToast(message: String, toastType: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, message, toastType).show()
}