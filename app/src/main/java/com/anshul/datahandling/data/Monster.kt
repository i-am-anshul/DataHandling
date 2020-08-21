package com.anshul.datahandling.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.anshul.datahandling.IMAGE_BASE_URL
import com.squareup.moshi.Json

//@Entity(tableName = "monsters")
data class Monster (
    //@PrimaryKey(autoGenerate = true)
    val monsterId: Int,
    val monsterName: String,
    val imageFile: String,
    val caption: String,
    val description: String,
    val price: Double,
    val scariness: Int
){
    val imageUrl
        get() = "${IMAGE_BASE_URL}/$imageFile.webp"
    val thumbnailUrl
        get() = "${IMAGE_BASE_URL}/${imageFile}_tn.webp"

}