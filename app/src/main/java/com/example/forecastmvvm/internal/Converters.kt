package com.example.forecastmvvm.internal

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {
    @TypeConverter
    fun listToJson(icons: List<String>?) = Gson().toJson(icons)

    @TypeConverter
    fun jsonToList(jsonValue: String) =
        Gson().fromJson(jsonValue, Array<String>::class.java).toList()
}