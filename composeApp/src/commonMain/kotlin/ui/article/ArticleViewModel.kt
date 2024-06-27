package ui.article

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import data.database.NewsDatabase
import data.model.Article
import data.repository.LocalNewsRepository
import kotlinx.coroutines.launch

class ArticleViewModel(
    newsDatabase: NewsDatabase
) : ViewModel() {
    private val localNewsRepository = LocalNewsRepository(newsDatabase.newsDao())
    var isBookmarked by mutableStateOf(false)

    fun isArticleBookmark(currentArticle: Article) {
        viewModelScope.launch {
            currentArticle.publishedAt.let {
                localNewsRepository.getArticle(it)?.let {
                    isBookmarked = true
                }
            }
        }
    }

    fun bookmarkArticle(currentArticle: Article) {
        viewModelScope.launch {
            if (!isBookmarked) {
                localNewsRepository.upsertArticle(currentArticle)
            } else localNewsRepository.deleteArticle(currentArticle)
        }
        isBookmarked = !isBookmarked
    }

}