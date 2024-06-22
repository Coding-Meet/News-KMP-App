package data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import data.model.Article

@Database(entities = [Article::class],version = 1, exportSchema = false)
abstract class NewsDatabase : RoomDatabase() {
    abstract fun newsDao(): NewsDao
}
