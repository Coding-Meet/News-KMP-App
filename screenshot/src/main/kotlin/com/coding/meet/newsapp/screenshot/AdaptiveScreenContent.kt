package com.coding.meet.newsapp.screenshot

import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.apadmi.cmpdevicepreview.devices.DevicePresets
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
    "01_two_pane_headlines_and_detail.png" to AdaptiveScreenState.HEADLINES,
    "02_two_pane_search_and_detail.png" to AdaptiveScreenState.SEARCH_RESULTS,
    "03_two_pane_bookmarks_and_detail.png" to AdaptiveScreenState.BOOKMARKS_SAVED,
    "04_two_pane_theme_dialog.png" to AdaptiveScreenState.THEME_DIALOG,
    "05_two_pane_delete_bookmarks_dialog.png" to AdaptiveScreenState.CLEAR_BOOKMARKS_DIALOG,
)

internal val webScreenStates = listOf(
    "01_web_headlines_and_detail.png" to AdaptiveScreenState.HEADLINES,
    "02_web_search_results.png" to AdaptiveScreenState.SEARCH_RESULTS,
    "03_web_bookmarks_and_detail.png" to AdaptiveScreenState.BOOKMARKS_SAVED,
    "04_web_theme_dialog.png" to AdaptiveScreenState.THEME_DIALOG,
    "05_web_delete_bookmarks_dialog.png" to AdaptiveScreenState.CLEAR_BOOKMARKS_DIALOG,
)

/**
 * Unified adaptive container that renders the given [state] automatically adapting to the
 * screen width:
 * - On compact devices (`maxWidth < 840.dp`, e.g. Pixel 9, iPhone 16 Pro): renders single-pane layout.
 * - On expanded screens (`maxWidth >= 840.dp`, e.g. Desktop window, Web browser): renders two-pane master-detail layout.
 */
@Composable
fun AdaptiveAppContent(
    state: AdaptiveScreenState,
    isDark: Boolean = true,
) {
    BoxWithConstraints(modifier = Modifier.fillMaxSize()) {
        val isTwoPane = maxWidth >= 840.dp

        if (isTwoPane) {
            when (state) {
                AdaptiveScreenState.HEADLINES,
                AdaptiveScreenState.HEADLINES_SHIMMER -> {
                    TwoPaneLayout(
                        leftPane = { HeadlinesContent() },
                        rightPane = { ArticleDetailContent() },
                    )
                }
                AdaptiveScreenState.ARTICLE_DETAIL -> {
                    TwoPaneLayout(
                        leftPane = { HeadlinesContent() },
                        rightPane = { ArticleDetailContent(isBookmarked = true) },
                    )
                }
                AdaptiveScreenState.SEARCH_EMPTY -> {
                    TwoPaneLayout(
                        leftPane = { SearchEmptyContent() },
                        rightPane = { ArticleDetailContent() },
                    )
                }
                AdaptiveScreenState.SEARCH_RESULTS -> {
                    TwoPaneLayout(
                        leftPane = { SearchResultsContent() },
                        rightPane = { ArticleDetailContent(article = sampleSearchArticles.first()) },
                    )
                }
                AdaptiveScreenState.BOOKMARKS_EMPTY -> {
                    TwoPaneLayout(
                        leftPane = { BookmarksEmptyContent() },
                        rightPane = { ArticleDetailContent() },
                    )
                }
                AdaptiveScreenState.BOOKMARKS_SAVED -> {
                    TwoPaneLayout(
                        leftPane = { BookmarksSavedContent() },
                        rightPane = { ArticleDetailContent(article = sampleBookmarkedArticles.first()) },
                    )
                }
                AdaptiveScreenState.SETTINGS -> {
                    TwoPaneLayout(
                        leftPane = { SettingsContent(isDark = isDark) },
                        rightPane = { ArticleDetailContent() },
                    )
                }
                AdaptiveScreenState.THEME_DIALOG -> {
                    TwoPaneLayout(
                        leftPane = { ThemeDialogContent(isDark = isDark) },
                        rightPane = { ArticleDetailContent() },
                    )
                }
                AdaptiveScreenState.CLEAR_BOOKMARKS_DIALOG -> {
                    TwoPaneLayout(
                        leftPane = { ClearBookmarksDialogContent(isDark = isDark) },
                        rightPane = { ArticleDetailContent() },
                    )
                }
            }
        } else {
            // Compact Mobile Layout
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

