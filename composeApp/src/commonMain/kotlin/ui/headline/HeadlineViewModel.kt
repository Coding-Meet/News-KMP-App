package ui.headline

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import data.model.Article
import data.model.ErrorResponse
import data.model.NewsResponse
import data.repository.OnlineNewsRepository
import io.ktor.client.call.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import utils.Resource

class HeadlineViewModel(
    private val onlineNewsRepository : OnlineNewsRepository
) : ViewModel() {

    private val _newsStateFlow =
        MutableStateFlow<Resource<List<Article>>>(Resource.Idle)
    val newsStateFlow: StateFlow<Resource<List<Article>>>
        get() = _newsStateFlow

    init {
        getHeadlines()
    }
    fun getHeadlines() {
        viewModelScope.launch(Dispatchers.IO) {
            _newsStateFlow.emit(Resource.Loading)
            try {
                val httpResponse = onlineNewsRepository.getNews()
                if (httpResponse.status.value in 200..299) {
                    val body = httpResponse.body<NewsResponse>()
                    _newsStateFlow.emit(Resource.Success(body.articles))
                } else {
                    val body = httpResponse.body<ErrorResponse>()
                    _newsStateFlow.emit(Resource.Error(body.message))
                }
            } catch (e: Exception) {
                _newsStateFlow.emit(Resource.Error(e.message.toString()))
            }

        }
    }

}