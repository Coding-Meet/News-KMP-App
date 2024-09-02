package data.repository

import com.coding.meet.newsapp.BuildKonfig.API_KEY
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
class OnlineNewsRepository(
    private val networkModule : HttpClient
) {

    suspend fun getNews(): HttpResponse {

        return networkModule.get {
            url("top-headlines")
            parameter("country", "us")
            parameter("apiKey", API_KEY)
        }

    }

    suspend fun searchNews(searchQuery: String): HttpResponse {
        return networkModule.get {
            url("everything")
            parameter("q", searchQuery)
            parameter("apiKey", API_KEY)
        }
    }
}