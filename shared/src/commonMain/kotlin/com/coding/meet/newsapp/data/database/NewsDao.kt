package com.coding.meet.newsapp.data.database

import androidx.room.*
import com.coding.meet.newsapp.data.local.entity.ArticleEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface NewsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(article: ArticleEntity)

    @Delete
    suspend fun delete(article: ArticleEntity)

    @Query("DELETE FROM articleTable")
    suspend fun deleteAllBookmark()

    @Query("SELECT * FROM articleTable")
    fun getArticles(): Flow<List<ArticleEntity>>

    @Query("SELECT * FROM articleTable WHERE publishedAt=:publishedAt")
    suspend fun getArticle(publishedAt: String): ArticleEntity?

}