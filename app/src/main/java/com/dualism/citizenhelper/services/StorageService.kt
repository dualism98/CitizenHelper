package com.dualism.citizenhelper.services

import android.content.Context
import android.content.SharedPreferences

class StorageService{
    fun setString(context: Context, key: String, string: String){
        val userData = context.getSharedPreferences(
            "user_storage",
            Context.MODE_PRIVATE
        )
        val e: SharedPreferences.Editor? = userData?.edit()
        e?.putString(key, string)
        e?.apply()
    }

    fun removeString(context: Context, key: String){
        val userData = context.getSharedPreferences(
            "user_storage",
            Context.MODE_PRIVATE
        )
        val e: SharedPreferences.Editor? = userData?.edit()
        e?.remove(key)
        e?.apply()
    }

    fun getString(context: Context, key: String, default: String): String?{
        val userData = context.getSharedPreferences(
            "user_storage",
            Context.MODE_PRIVATE
        )
        val res = userData?.getString(key, default)
        return(res)
    }
}