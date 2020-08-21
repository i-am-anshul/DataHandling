package com.anshul.datahandling.ui.shared

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.anshul.datahandling.LOG_TAG
import com.anshul.datahandling.data.Monster
import com.anshul.datahandling.data.MonsterRepo
import com.anshul.datahandling.utilities.FileHelper
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

class SharedViewModel(app: Application) : AndroidViewModel(app) {


    private val dataRepo = MonsterRepo(app)
    val monsterData = dataRepo.monsterData

    val selectedMonster= MutableLiveData<Monster>()

    fun refreshData() {
        dataRepo.refreshData()
    }
}
