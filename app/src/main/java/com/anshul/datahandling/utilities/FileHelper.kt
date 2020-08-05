package com.anshul.datahandling.utilities

import android.content.Context

class FileHelper {
    companion object{
        fun getTextFromResources(context: Context, resouceId: Int) : String{
            return context.resources.openRawResource(resouceId).use {
                it.bufferedReader().use {
                    it.readText()

                }
            }
        }

        fun getTextFromAssets(context: Context, fileName: String) : String{
            return context.assets.open(fileName).use {
                it.bufferedReader().use {
                    it.readText()

                }
            }
        }
    }
}