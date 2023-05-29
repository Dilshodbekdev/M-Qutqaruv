package com.technocorp.mqutqaruv.util

import android.content.Context
import android.content.SharedPreferences

class SharedPref constructor(context: Context) {

    private val preferences: SharedPreferences = context.getSharedPreferences(NAME, MODE)

    companion object {

        private const val NAME = "MQutqaruv"
        private const val MODE = Context.MODE_PRIVATE
        private var instance: SharedPref? = null
        fun getInstance(context: Context): SharedPref {
            return if (instance != null) {
                instance as SharedPref
            } else {
                instance = SharedPref(context)
                instance as SharedPref
            }
        }
    }

    private inline fun SharedPreferences.edit(operation: (SharedPreferences.Editor) -> Unit) {
        val editor = edit()
        operation(editor)
        editor.apply()
    }

    var register: Boolean?
        get() = preferences.getBoolean("register", false)
        set(value) = preferences.edit {
            if (value != null) {
                it.putBoolean("register", value)
            }
        }

    var token: String?
        get() = preferences.getString("access_token", "")
        set(value) = preferences.edit {
            if (value != null) {
                it.putString("access_token", value)
            }
        }
}