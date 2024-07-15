package ui.setting

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import data.repository.LocalNewsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import utils.AppPreferences

class SettingViewModel(
    private val appPreferences: AppPreferences,
    private val localNewsRepository : LocalNewsRepository
) : ViewModel() {

    private val _isDarkModeEnabled = MutableStateFlow(false)
    val isDarkModeEnabled = _isDarkModeEnabled.asStateFlow()

    fun deleteHistory() = viewModelScope.launch(Dispatchers.IO) {
        localNewsRepository.deleteAllBookmark()
    }

    private val _isInit = MutableStateFlow(false)
    val isInit = _isInit.asStateFlow()


    init {
        isDarkModeEnabled()
        _isInit.value = true
    }

    private fun isDarkModeEnabled() = viewModelScope.launch(Dispatchers.IO) {
        _isDarkModeEnabled.value = appPreferences.isDarkModeEnabled()
    }

    fun changeDarkMode(isEnabled: Boolean) = viewModelScope.launch(Dispatchers.IO) {
        appPreferences.changeDarkMode(isEnabled)
        _isDarkModeEnabled.value = isEnabled
    }
}