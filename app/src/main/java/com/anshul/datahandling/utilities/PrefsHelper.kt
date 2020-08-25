package com.anshul.datahandling.utilities

import android.content.Context
import android.content.SharedPreferences

const val ITEM_TYPE_KEY = "item_type_key"


class PrefsHelper {
    companion object{
        private fun preferences(context: Context): SharedPreferences =
            context.getSharedPreferences("default", 0)

        fun setItemType(context: Context, itemType: String){
            preferences(context).edit()
                .putString(ITEM_TYPE_KEY,itemType)
                .apply()
        }

        fun getItemType(context: Context): String =
            preferences(context).getString(ITEM_TYPE_KEY, "list")!!

    }
}