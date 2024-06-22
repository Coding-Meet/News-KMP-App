package data.repository

import data.database.NewsDao
import data.model.Article
import kotlinx.coroutines.flow.Flow

class NewsRepository(
//    private val newsApi: NewsApi,
    private val newsDao: NewsDao
)  {

//     fun getNews(sources: List<String>): Flow<PagingData<Article>> {
//        return Pager(
//            config = PagingConfig(pageSize = 10),
//            pagingSourceFactory = {
//                NewsPagingSource(newsApi = newsApi, sources = sources.joinToString(separator = ","))
//            }
//        ).flow
//    }
//
//     fun searchNews(searchQuery: String, sources: List<String>): Flow<PagingData<Article>> {
//        return Pager(
//            config = PagingConfig(pageSize = 10),
//            pagingSourceFactory = {
//                SearchNewsPagingSource(
//                    api = newsApi,
//                    searchQuery = searchQuery,
//                    sources = sources.joinToString(separator = ",")
//                )
//            }
//        ).flow
//    }

     suspend fun upsertArticle(article: Article) {
       newsDao.upsert(article)
    }

     suspend fun deleteArticle(article: Article) {
        newsDao.delete(article)
    }

     fun getArticles(): Flow<List<Article>> {
        return newsDao.getArticles()
    }

     suspend fun getArticle(articleId: String): Article? {
        return newsDao.getArticle(articleId = articleId)
    }
}