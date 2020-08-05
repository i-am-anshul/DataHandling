package com.anshul.datahandling.ui.main

import android.app.Application
import android.util.JsonReader
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import com.anshul.datahandling.LOG_TAG
import com.anshul.datahandling.R
import com.anshul.datahandling.data.Monster
import com.anshul.datahandling.utilities.FileHelper
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import kotlin.reflect.typeOf

class MainViewModel(app: Application) : AndroidViewModel(app) {

    private val listType = Types.newParameterizedType(
            List::class.java, Monster::class.java
    )

    init {
//        val text = FileHelper.getTextFromResources(app, R.raw.monster_data)
        val text = FileHelper.getTextFromAssets(app, "monster_data.json")
        parseText(text)
    }

    fun parseText(text: String) {
        val moshi = Moshi.Builder().build()
        val adapter: JsonAdapter<List<Monster>> =
                moshi.adapter(listType)
        val monsterData = adapter.fromJson(text)

        for (monster in monsterData ?: emptyList()) {
            Log.i(LOG_TAG,
                    "${monster.monsterName} (\$${monster.price})")
        }
    }

}