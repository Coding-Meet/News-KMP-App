package com.coding.meet.newsapp.screenshot

import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteType
import androidx.compose.material3.adaptive.navigationsuite.rememberNavigationSuiteScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.apadmi.cmpdevicepreview.devices.DevicePresets
import com.coding.meet.newsapp.ui.navigation.CustomNavigationSuiteScaffoldLayout
import com.coding.meet.newsapp.ui.navigation.NavigationItem
import java.io.File

/**
 * All canonical screen states represented in the News KMP App screenshot suite.
 */
enum class AdaptiveScreenState {
    HEADLINES,
    HEADLINES_SHIMMER,
    ARTICLE_DETAIL,
    SEARCH_EMPTY,
    SEARCH_RESULTS,
    BOOKMARKS_EMPTY,
    BOOKMARKS_SAVED,
    SETTINGS,
    THEME_DIALOG,
    CLEAR_BOOKMARKS_DIALOG,
}

/**
 * Target device platforms supported by the unified adaptive renderer.
 */
enum class TargetPlatform {
    ANDROID,
    IOS,
    MACOS,
    WEB,
}

internal val mobileScreenStates = listOf(
    "01_headlines_feed.png" to AdaptiveScreenState.HEADLINES,
    "02_headlines_shimmer_loading.png" to AdaptiveScreenState.HEADLINES_SHIMMER,
    "03_article_detail.png" to AdaptiveScreenState.ARTICLE_DETAIL,
    "04_search_empty.png" to AdaptiveScreenState.SEARCH_EMPTY,
    "05_search_results.png" to AdaptiveScreenState.SEARCH_RESULTS,
    "06_bookmarks_empty.png" to AdaptiveScreenState.BOOKMARKS_EMPTY,
    "07_bookmarks_saved.png" to AdaptiveScreenState.BOOKMARKS_SAVED,
    "08_settings_screen.png" to AdaptiveScreenState.SETTINGS,
    "09_theme_selection_dialog.png" to AdaptiveScreenState.THEME_DIALOG,
    "10_delete_bookmarks_dialog.png" to AdaptiveScreenState.CLEAR_BOOKMARKS_DIALOG,
)

internal val desktopScreenStates = listOf(
    "01_headlines_feed.png" to AdaptiveScreenState.HEADLINES,
    "02_article_detail.png" to AdaptiveScreenState.ARTICLE_DETAIL,
    "03_search_results.png" to AdaptiveScreenState.SEARCH_RESULTS,
    "04_bookmarks_saved.png" to AdaptiveScreenState.BOOKMARKS_SAVED,
    "05_settings_screen.png" to AdaptiveScreenState.SETTINGS,
    "06_theme_selection_dialog.png" to AdaptiveScreenState.THEME_DIALOG,
)

internal val webScreenStates = listOf(
    "01_web_headlines_feed.png" to AdaptiveScreenState.HEADLINES,
    "02_web_article_detail.png" to AdaptiveScreenState.ARTICLE_DETAIL,
    "03_web_search_results.png" to AdaptiveScreenState.SEARCH_RESULTS,
    "04_web_bookmarks_saved.png" to AdaptiveScreenState.BOOKMARKS_SAVED,
    "05_web_settings_screen.png" to AdaptiveScreenState.SETTINGS,
    "06_web_theme_selection_dialog.png" to AdaptiveScreenState.THEME_DIALOG,
)

/**
 * Canonical adaptive app container powered by [CustomNavigationSuiteScaffoldLayout]:
 * - Compact screens (< 840.dp, e.g. Pixel 9, iPhone 16 Pro):
 *   Uses [NavigationSuiteType.ShortNavigationBarCompact] with active item selection, or
 *   [NavigationSuiteType.None] when viewing article details / settings.
 * - Expanded screens (>= 840.dp, e.g. Desktop, Web):
 *   Uses [NavigationSuiteType.NavigationDrawer] permanent navigation panel on the left, or
 *   [NavigationSuiteType.None] for detail / settings view.
 */
@Composable
fun AdaptiveAppContent(
    state: AdaptiveScreenState,
    isDark: Boolean = true,
) {
    BoxWithConstraints(modifier = Modifier.fillMaxSize()) {
        val isExpanded = maxWidth >= 840.dp

        val currentNavItem = when (state) {
            AdaptiveScreenState.HEADLINES,
            AdaptiveScreenState.HEADLINES_SHIMMER -> NavigationItem.Headlines
            AdaptiveScreenState.SEARCH_EMPTY,
            AdaptiveScreenState.SEARCH_RESULTS -> NavigationItem.Search
            AdaptiveScreenState.BOOKMARKS_EMPTY,
            AdaptiveScreenState.BOOKMARKS_SAVED -> NavigationItem.Bookmark
            AdaptiveScreenState.ARTICLE_DETAIL,
            AdaptiveScreenState.SETTINGS,
            AdaptiveScreenState.THEME_DIALOG,
            AdaptiveScreenState.CLEAR_BOOKMARKS_DIALOG -> null
        }

        val layoutType = if (currentNavItem == null) {
            NavigationSuiteType.None
        } else if (isExpanded) {
            NavigationSuiteType.NavigationDrawer
        } else {
            NavigationSuiteType.ShortNavigationBarCompact
        }

        CustomNavigationSuiteScaffoldLayout(
            navigationSuiteState = rememberNavigationSuiteScaffoldState(),
            layoutType = layoutType,
            currentNavigationItem = currentNavItem,
            onNavigationItemClick = {},
        ) {
            when (state) {
                AdaptiveScreenState.HEADLINES -> HeadlinesContent()
                AdaptiveScreenState.HEADLINES_SHIMMER -> HeadlinesShimmerContent()
                AdaptiveScreenState.ARTICLE_DETAIL -> ArticleDetailContent()
                AdaptiveScreenState.SEARCH_EMPTY -> SearchEmptyContent()
                AdaptiveScreenState.SEARCH_RESULTS -> SearchResultsContent()
                AdaptiveScreenState.BOOKMARKS_EMPTY -> BookmarksEmptyContent()
                AdaptiveScreenState.BOOKMARKS_SAVED -> BookmarksSavedContent()
                AdaptiveScreenState.SETTINGS -> SettingsContent(isDark = isDark)
                AdaptiveScreenState.THEME_DIALOG -> ThemeDialogContent(isDark = isDark)
                AdaptiveScreenState.CLEAR_BOOKMARKS_DIALOG -> ClearBookmarksDialogContent(isDark = isDark)
            }
        }
    }
}

/**
 * High-level driver that iterates all screen states for [platform] and exports them to [outputDir].
 */
internal fun exportPlatformScreens(
    platform: TargetPlatform,
    outputDir: File,
    isDark: Boolean,
) {
    when (platform) {
        TargetPlatform.ANDROID -> {
            for ((filename, state) in mobileScreenStates) {
                val outFile = File(outputDir, filename)
                renderPhoneScreen(DevicePresets.pixel9, outFile, isDark = isDark) {
                    AdaptiveAppContent(state = state, isDark = isDark)
                }
            }
        }
        TargetPlatform.IOS -> {
            for ((filename, state) in mobileScreenStates) {
                val outFile = File(outputDir, filename)
                renderPhoneScreen(DevicePresets.iPhone16Pro, outFile, isDark = isDark) {
                    AdaptiveAppContent(state = state, isDark = isDark)
                }
            }
        }
        TargetPlatform.MACOS -> {
            for ((filename, state) in desktopScreenStates) {
                val outFile = File(outputDir, filename)
                renderDesktopScreen(DevicePresets.largeDesktopWindow, listOf(outFile), isDark = isDark) {
                    AdaptiveAppContent(state = state, isDark = isDark)
                }
            }
        }
        TargetPlatform.WEB -> {
            for ((filename, state) in webScreenStates) {
                val outFile = File(outputDir, filename)
                renderWebBrowserScreen(outFile, isDark = isDark) {
                    AdaptiveAppContent(state = state, isDark = isDark)
                }
            }
        }
    }
}
