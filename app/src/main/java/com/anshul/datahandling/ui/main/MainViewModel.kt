package com.anshul.datahandling.ui.main

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import com.anshul.datahandling.LOG_TAG
import com.anshul.datahandling.utilities.FileHelper

class MainViewModel(app: Application) : AndroidViewModel(app) {

    init {
        val text = FileHelper.getTextFromAssets(app, "monster_data.json")
        //val text = FileHelper.getTextFromResources(app, R.raw.monster_data)

        Log.i(LOG_TAG, text)

    }

}