package com.anshul.datahandling.data

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.util.Log
import androidx.annotation.WorkerThread
import androidx.lifecycle.MutableLiveData
import com.anshul.datahandling.LOG_TAG
import com.anshul.datahandling.WEB_SERVICE_URL
import com.anshul.datahandling.utilities.FileHelper
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class MonsterRepo(val app: Application) {

    val monsterData =  MutableLiveData<List<Monster>>()

    private val listType = Types.newParameterizedType(
        List::class.java, Monster::class.java
    )

    init {
        val data = readDataFromCache()
        if(data.isEmpty()){
            refreshDataFromWeb()
            Log.e(LOG_TAG,"Data from web")
        }else{
            monsterData.value = data
            Log.e(LOG_TAG,"Data from local")
        }

    }
    @WorkerThread
    suspend fun callWebService(){
        if(checkNetworkAvailability()){
            val retrofit = Retrofit.Builder()
                .baseUrl(WEB_SERVICE_URL)
                .addConverterFactory(MoshiConverterFactory.create())
                .build()

            val service = retrofit.create(MonsterService::class.java)

            val serviceData = service.getMonsterData().body() ?: emptyList()
            monsterData.postValue(serviceData)

            saveDataToCache(serviceData)
        }
    }

//    fun getMonsterData() {
//
//        val text = FileHelper.getTextFromAssets(app, "monster_data.json")
//
//        val moshi = Moshi
//            .Builder()
//            .add(KotlinJsonAdapterFactory())
//            .build()
//        val adapter: JsonAdapter<List<Monster>> =
//            moshi.adapter(listType)
//        monsterData.value =  adapter.fromJson(text) ?: emptyList()
//    }

    fun checkNetworkAvailability(): Boolean {
        val connectivityManager = app.getSystemService(Context.CONNECTIVITY_SERVICE)
            as ConnectivityManager

        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo?.isConnectedOrConnecting ?: false
    }

    fun refreshDataFromWeb() {
        CoroutineScope(Dispatchers.IO).launch {
            callWebService()
        }
    }

    private fun saveDataToCache(monsterData: List<Monster>){
        val moshi = Moshi.Builder().build()
        val listType = Types.newParameterizedType(List::class.java, Monster::class.java)
        val adapter: JsonAdapter<List<Monster>> = moshi.adapter(listType)
        val json = adapter.toJson(monsterData)
        FileHelper.saveTextToFile(app, json)
    }

    private fun readDataFromCache(): List<Monster>{
        val json = FileHelper.readTextFromFile(app)
        if(json == null){
            return emptyList()
        }else{
            return getDataFromJson(json)
        }
    }

    private fun getDataFromJson(json: String?): List<Monster> {
        val moshi = Moshi.Builder().build()
        val listType = Types.newParameterizedType(List::class.java, Monster::class.java)
        val adapter: JsonAdapter<List<Monster>> = moshi.adapter(listType)
        return adapter.fromJson(json) ?: emptyList()
    }

}