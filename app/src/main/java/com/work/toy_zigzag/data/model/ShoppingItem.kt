package com.work.toy_zigzag.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ShoppingItem(
    val list: List<ShoppingDocumentsItem>,
    val week: String
) : Parcelable