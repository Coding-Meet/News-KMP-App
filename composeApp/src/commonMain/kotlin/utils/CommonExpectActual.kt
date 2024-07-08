package utils

import androidx.room.RoomDatabase
import data.database.NewsDatabase

expect fun shareLink(url: String)

expect fun randomUUIDStr():String
expect fun getType():Type

expect fun getDatabaseBuilder() : RoomDatabase.Builder<NewsDatabase>