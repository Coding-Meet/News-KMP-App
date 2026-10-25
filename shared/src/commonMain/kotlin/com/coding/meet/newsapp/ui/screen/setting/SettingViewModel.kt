package com.coding.meet.newsapp.ui.screen.setting

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.coding.meet.newsapp.domain.usecase.ClearBookmarksUseCase
import com.coding.meet.newsapp.domain.usecase.GetThemeUseCase
import com.coding.meet.newsapp.domain.usecase.SetThemeUseCase
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SettingViewModel(
    private val getTheme: GetThemeUseCase,
    private val setTheme: SetThemeUseCase,
    private val clearBookmarks: ClearBookmarksUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow(SettingState())
    val state = _state.asStateFlow()

    private val _effects = Channel<SettingEffect>(Channel.BUFFERED)
    val effects = _effects.receiveAsFlow()

    init {
        getTheme()
            .onEach { theme -> _state.update { it.copy(currentTheme = theme) } }
            .launchIn(viewModelScope)
    }

    fun onEvent(event: SettingEvent) {
        when (event) {
            is SettingEvent.ChangeTheme -> viewModelScope.launch { setTheme(event.theme) }
            SettingEvent.ShowThemeDialog -> _state.update { it.copy(showThemeDialog = true) }
            SettingEvent.DismissThemeDialog -> _state.update { it.copy(showThemeDialog = false) }
            SettingEvent.ShowDeleteDialog -> _state.update { it.copy(showDeleteDialog = true) }
            SettingEvent.DismissDeleteDialog -> _state.update { it.copy(showDeleteDialog = false) }
            SettingEvent.ConfirmDeleteHistory -> {
                _state.update { it.copy(showDeleteDialog = false) }
                viewModelScope.launch { clearBookmarks() }
            }

            SettingEvent.NavigateBack -> {
                viewModelScope.launch { _effects.send(SettingEffect.NavigateBack) }
            }
        }
    }
}
