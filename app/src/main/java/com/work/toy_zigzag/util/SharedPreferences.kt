package com.work.toy_zigzag.util

import android.content.Context
import android.content.SharedPreferences

class SharedPreferences(context: Context) {

    private val prefs: SharedPreferences =
        context.getSharedPreferences(PREFS_FILENAME, 0)

    var styleList: String
        get() = prefs.getString(PREF_KEY_STYLE_LIST, EMPTY_TEXT)!!
        set(value) = prefs.edit().putString(PREF_KEY_STYLE_LIST, value).apply()

    var selectFilter: String
        get() = prefs.getString(PREF_KEY_SELECT_FILTER, EMPTY_TEXT)!!
        set(value) = prefs.edit().putString(PREF_KEY_SELECT_FILTER, value).apply()


    companion object {
        private const val PREFS_FILENAME = "prefs"
        private const val EMPTY_TEXT = ""
        private const val PREF_KEY_STYLE_LIST = "style_list"
        private const val PREF_KEY_SELECT_FILTER = "select_filter"
    }
}