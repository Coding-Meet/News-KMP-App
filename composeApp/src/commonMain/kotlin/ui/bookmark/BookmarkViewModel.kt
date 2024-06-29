package ui.bookmark

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import data.database.NewsDatabase
import data.model.Article
import data.repository.LocalNewsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import utils.Resource

class BookmarkViewModel(newsDatabase: NewsDatabase) : ViewModel() {

    private val localNewsRepository = LocalNewsRepository(newsDatabase.newsDao())


    private val _bookmarkNewsStateFlow =
        MutableStateFlow<Resource<List<Article>>>(Resource.Idle)
    val bookmarkNewsStateFlow: StateFlow<Resource<List<Article>>>
        get() = _bookmarkNewsStateFlow
    init {
        getArticles()
    }

    fun getArticles() {
        viewModelScope.launch(Dispatchers.IO) {
            _bookmarkNewsStateFlow.emit(Resource.Loading)
            localNewsRepository.getArticles().catch {
                it.printStackTrace()
                _bookmarkNewsStateFlow.emit(Resource.Error(it.message.toString()))
            }.collect { result ->
                _bookmarkNewsStateFlow.emit(Resource.Success(result))
            }
        }
    }
}