package data.database

import androidx.room.*
import data.model.Article
import kotlinx.coroutines.flow.Flow

@Dao
interface NewsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(article: Article)

    @Delete
    suspend fun delete(article: Article)

    @Query("DELETE FROM articleTable")
    suspend fun deleteAllBookmark()

    @Query("SELECT * FROM articleTable")
    fun getArticles(): Flow<List<Article>>

    @Query("SELECT * FROM articleTable WHERE articleId=:articleId")
    suspend fun getArticle(articleId: String): Article?

}