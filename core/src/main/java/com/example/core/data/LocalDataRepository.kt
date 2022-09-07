package com.example.core.data

import android.content.Context
import android.content.SharedPreferences
import com.example.core.data.model.ResponseDTOModel
import com.google.gson.Gson

interface LocalDataRepository {

    fun saveResponse(items: ResponseDTOModel): Boolean

    fun readResponse(): ResponseDTOModel?


    class Base(private val context: Context) : LocalDataRepository {

        private val sharedPreferences: SharedPreferences =
            context.getSharedPreferences(this::class.java.simpleName, Context.MODE_PRIVATE)

        override fun saveResponse(items: ResponseDTOModel): Boolean {
            return try {
                sharedPreferences.edit().putString(ITEMS, Gson().toJson(items)).apply()
                true
            } catch (e: Exception) {
                e.printStackTrace()
                false
            }
        }

        override fun readResponse(): ResponseDTOModel? {

            return try {
                val json = sharedPreferences.getString(ITEMS, null)
                if (json != null) {
                   return Gson().fromJson(json, getClazz())
                }
                null
            } catch (e: Exception) {
                e.printStackTrace()
                null
            }
        }

        private inline fun <reified T> getClazz(): Class<T> {
            return T::class.java
        }

        private companion object {
            const val ITEMS = "items"
        }

    }

}