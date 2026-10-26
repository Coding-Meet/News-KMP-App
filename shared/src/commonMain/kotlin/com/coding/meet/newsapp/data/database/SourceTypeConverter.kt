package com.coding.meet.newsapp.data.database

import androidx.room.TypeConverter
import com.coding.meet.newsapp.data.local.entity.SourceEntity
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class SourceTypeConverter {

    @TypeConverter
    fun fromSourceToString(value: SourceEntity): String = Json.encodeToString(value)

    @TypeConverter
    fun fromStringToSource(value: String): SourceEntity = Json.decodeFromString(value)

}