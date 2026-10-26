package com.coding.meet.newsapp.utils

import androidx.room.RoomDatabase
import com.coding.meet.newsapp.data.database.NewsDatabase

expect fun shareLink(url: String)

expect fun getDatabaseBuilder(): RoomDatabase.Builder<NewsDatabase>