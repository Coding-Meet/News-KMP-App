package com.coding.meet.newsapp.ui.setting

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.coding.meet.newsapp.designsystem.component.topappbar.NewsTopAppBar
import com.coding.meet.newsapp.designsystem.preview.AllAdaptivePreviews
import com.coding.meet.newsapp.designsystem.theme.NewsAppTheme
import com.coding.meet.newsapp.ui.setting.component.BookmarkDialog
import com.coding.meet.newsapp.ui.setting.component.SettingItem
import com.coding.meet.newsapp.ui.setting.component.ThemeSelectionDialog
import com.coding.meet.newsapp.utils.Theme
import news_kmp_app.shared.generated.resources.Res
import news_kmp_app.shared.generated.resources.delete_bookmark
import news_kmp_app.shared.generated.resources.go_back
import news_kmp_app.shared.generated.resources.ic_delete
import news_kmp_app.shared.generated.resources.ic_light_mode
import news_kmp_app.shared.generated.resources.setting
import news_kmp_app.shared.generated.resources.theme
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun SettingScreen(
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
                    painter = painterResource(Res.drawable.ic_light_mode),
                    itemName = stringResource(Res.string.theme),
                )
            }
            item {
                SettingItem(
                    onClick = { onEvent(SettingEvent.ShowDeleteDialog) },
                    painter = painterResource(Res.drawable.ic_delete),
                    itemName = stringResource(Res.string.delete_bookmark),
                    itemColor = MaterialTheme.colorScheme.error,
                )
            }
        }

        if (state.showThemeDialog) {
            ThemeSelectionDialog(
                onThemeChange = { theme -> onEvent(SettingEvent.ChangeTheme(theme.name)) },
                onDismissRequest = { onEvent(SettingEvent.DismissThemeDialog) },
                currentTheme = state.currentTheme.ifBlank { Theme.DARK_MODE.name },
            )
        }
    }
}

@AllAdaptivePreviews
@Composable
private fun SettingScreenPreview() {
    NewsAppTheme(appTheme = null, darkTheme = isSystemInDarkTheme()) {
        SettingScreen(
            state = SettingState(currentTheme = Theme.DARK_MODE.name),
            onEvent = {},
        )
    }
}

@AllAdaptivePreviews
@Composable
private fun SettingScreenThemeDialogPreview() {
    NewsAppTheme(appTheme = null, darkTheme = isSystemInDarkTheme()) {
        SettingScreen(
            state = SettingState(
                currentTheme = Theme.DARK_MODE.name,
                showThemeDialog = true,
            ),
            onEvent = {},
        )
    }
}
