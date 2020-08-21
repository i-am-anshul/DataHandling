package com.anshul.datahandling.ui.shared

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.anshul.datahandling.data.Monster
import com.anshul.datahandling.data.MonsterRepo

class SharedViewModel(app: Application) : AndroidViewModel(app) {


    private val dataRepo = MonsterRepo(app)
    val monsterData = dataRepo.monsterData

    val selectedMonster= MutableLiveData<Monster>()

    fun refreshData() {
        dataRepo.refreshDataFromWeb()
    }
}
