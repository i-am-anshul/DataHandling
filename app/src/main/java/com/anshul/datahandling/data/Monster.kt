package com.anshul.datahandling.data

import com.squareup.moshi.Json

data class Monster (

    val monsterName: String,
    val imageFile: String,
    val caption: String,
    val description: String,
    val price: Double,
    val scariness: Int
)