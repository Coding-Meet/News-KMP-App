package data.repository

import com.coding.meet.newsapp.BuildKonfig.API_KEY
import io.ktor.client.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.client.plugins.websocket.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import utils.BASE_URL

class OnlineNewsRepository {

    @OptIn(ExperimentalSerializationApi::class)
    val networkModule = HttpClient {
        install(HttpTimeout) {
            socketTimeoutMillis = 60_000
            requestTimeoutMillis = 60_000
        }
        install(Logging) {
            logger = Logger.DEFAULT
            level = LogLevel.ALL
            logger = object : Logger {
                override fun log(message: String) {
                    co.touchlab.kermit.Logger.d(tag = "KtorClient", null) {
                        message
                    }
                }
            }
        }
        defaultRequest {
            url(BASE_URL)
            contentType(ContentType.Application.Json)
        }
        install(ContentNegotiation) {
            json(Json {
                prettyPrint = true
                isLenient = true
                ignoreUnknownKeys = true
                explicitNulls = false
            })
        }
        install(WebSockets)

    }

    suspend fun getNews(): HttpResponse {

        return networkModule.get {
            url("top-headlines")
            parameter("country", "us")
            parameter("page", 1)
            parameter("apiKey", API_KEY)
        }

    }

    suspend fun searchNews(searchQuery: String): HttpResponse {
        return networkModule.get {
            url("everything")
            parameter("q", searchQuery)
            parameter("page", 1)
            parameter("apiKey", API_KEY)
        }
    }
}