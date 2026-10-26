package com.coding.meet.newsapp.ui.screen.setting

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.Palette
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.coding.meet.newsapp.ui.designsystem.component.topappbar.NewsTopAppBar
import com.coding.meet.newsapp.ui.designsystem.preview.AllAdaptivePreviews
import com.coding.meet.newsapp.ui.designsystem.theme.NewsAppTheme
import com.coding.meet.newsapp.ui.screen.setting.components.BookmarkDialog
import com.coding.meet.newsapp.ui.screen.setting.components.SettingItem
import com.coding.meet.newsapp.ui.screen.setting.components.ThemeSelectionDialog
import com.coding.meet.newsapp.utils.Theme
import news_kmp_app.shared.generated.resources.Res
import news_kmp_app.shared.generated.resources.delete_bookmark
import news_kmp_app.shared.generated.resources.go_back
import news_kmp_app.shared.generated.resources.setting
import news_kmp_app.shared.generated.resources.theme
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun SettingScreen(
    onNavigateBack: () -> Unit,
) {
    val viewModel = koinViewModel<SettingViewModel>()

    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.effects.collect { effect ->
            when (effect) {
                SettingEffect.NavigateBack -> onNavigateBack()
            }
        }
    }

    SettingScreenCotent(
        state = state,
        onEvent = viewModel::onEvent,
    )
}


@Composable
fun SettingScreenCotent(
    state: SettingState,
    onEvent: (SettingEvent) -> Unit,
) {
    if (state.showDeleteDialog) {
        BookmarkDialog(
            onDeleteHistory = { onEvent(SettingEvent.ConfirmDeleteHistory) },
            onDismissRequest = { onEvent(SettingEvent.DismissDeleteDialog) },
        )
    }

    Scaffold(
        topBar = {
            NewsTopAppBar(
                title = stringResource(Res.string.setting),
                navigationIcon = {
                    IconButton(onClick = { onEvent(SettingEvent.NavigateBack) }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = stringResource(Res.string.go_back),
                            tint = MaterialTheme.colorScheme.onBackground,
                        )
                    }
                },
            )
        },
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
        ) {
            item {
                SettingItem(
                    onClick = { onEvent(SettingEvent.ShowThemeDialog) },
                    icon = Icons.Outlined.Palette,
                    itemName = stringResource(Res.string.theme),
                )
            }
            item {
                SettingItem(
                    onClick = { onEvent(SettingEvent.ShowDeleteDialog) },
                    icon = Icons.Outlined.Delete,
                    itemName = stringResource(Res.string.delete_bookmark),
                    itemColor = MaterialTheme.colorScheme.error,
                )
            }
        }

        if (state.showThemeDialog) {
            ThemeSelectionDialog(
                onThemeChange = { theme -> onEvent(SettingEvent.ChangeTheme(theme.name)) },
                onDismissRequest = { onEvent(SettingEvent.DismissThemeDialog) },
                currentTheme = state.currentThemeName.ifBlank { Theme.DARK_MODE.name },
            )
        }
    }
}

@AllAdaptivePreviews
@Composable
private fun SettingScreenCotentPreview() {
    NewsAppTheme(appTheme = null, darkTheme = isSystemInDarkTheme()) {
        SettingScreenCotent(
            state = SettingState(currentThemeName = Theme.DARK_MODE.name),
            onEvent = {},
        )
    }
}

@AllAdaptivePreviews
@Composable
private fun SettingScreenCotentThemeDialogPreview() {
    NewsAppTheme(appTheme = null, darkTheme = isSystemInDarkTheme()) {
        SettingScreenCotent(
            state = SettingState(
                currentThemeName = Theme.DARK_MODE.name,
                showThemeDialog = true,
            ),
            onEvent = {},
        )
    }
}
