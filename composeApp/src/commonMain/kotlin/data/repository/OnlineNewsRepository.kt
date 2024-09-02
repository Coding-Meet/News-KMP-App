package data.repository

import com.coding.meet.newsapp.BuildKonfig.API_KEY
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
class OnlineNewsRepository(
    private val networkModule : HttpClient
) {

    suspend fun getNews(category: String): HttpResponse {

        return networkModule.get {
//             top headline prefect work but image url not get so i change it
//            url("top-headlines")
//            parameter("country", "us")
//            parameter("apiKey", API_KEY)

//             here only category it prefect work image url not get, so I use everything api
//            url("top-headlines")
//            parameter("category",category)
//            parameter("apiKey", API_KEY)

            url("everything")
            parameter("q", category)
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