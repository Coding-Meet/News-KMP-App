package data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "articleTable")
data class Article(
    val source: String,
    val author: String?,
    val title: String,
    val description: String?,
    val url: String,
    val urlToImage: String?,
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name="articleId")
    val publishedAt: String,
    val content: String?
)
//@Entity(tableName = "articleTable")
//data class Article(
//    val source: String="",
//    val author: String = "",
//    val title: String = "",
//    val description: String="",
//    val url: String="",
//    val urlToImage: String="",
//    @PrimaryKey(autoGenerate = false)
//    @ColumnInfo(name="articleId")
//    val publishedAt: String="",
//    val content: String=""
//)