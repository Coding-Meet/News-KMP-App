package com.coding.meet.newsapp.designsystem.component.navigation

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.only
import androidx.compose.material3.DrawerDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.NavigationRailDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfoV2
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffoldState
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffoldValue
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteType
import androidx.compose.material3.adaptive.navigationsuite.rememberNavigationSuiteScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.coding.meet.newsapp.designsystem.preview.AllAdaptivePreviews
import com.coding.meet.newsapp.designsystem.theme.NewsAppTheme
import com.coding.meet.newsapp.ui.navigation.customNavigationSuiteType
import com.coding.meet.newsapp.utils.Theme
import com.coding.meet.newsapp.utils.navigationItemsLists

@Composable
fun MainContentBox(
    navigationSuiteState: NavigationSuiteScaffoldState,
    layoutType: NavigationSuiteType,
    content: @Composable BoxScope.() -> Unit
) {
    Box(
        modifier = Modifier.fillMaxSize().consumeWindowInsets(
            if (
                navigationSuiteState.currentValue == NavigationSuiteScaffoldValue.Hidden &&
                !navigationSuiteState.isAnimating
            ) {
                WindowInsets()
            } else {
                when (layoutType) {
                    NavigationSuiteType.ShortNavigationBarCompact,
                    NavigationSuiteType.ShortNavigationBarMedium,
                    NavigationSuiteType.NavigationBar ->
                        NavigationBarDefaults.windowInsets.only(WindowInsetsSides.Bottom)

                    NavigationSuiteType.NavigationRail ->
                        NavigationRailDefaults.windowInsets.only(WindowInsetsSides.Start)

                    NavigationSuiteType.NavigationDrawer ->
                        DrawerDefaults.windowInsets.only(WindowInsetsSides.Start)

                    else -> WindowInsets()
                }
            }
        ),
        content = content
    )
}

@AllAdaptivePreviews
@Composable
private fun MainContentBoxPreview() {
    NewsAppTheme(
        appTheme = Theme.SYSTEM_DEFAULT.name,
        darkTheme = isSystemInDarkTheme(),
    ) {
        val windowAdaptiveInfo = currentWindowAdaptiveInfoV2()
        val layoutType = customNavigationSuiteType(windowAdaptiveInfo)
        SystemNavigationSuiteScaffold(
            navigationSuiteState = rememberNavigationSuiteScaffoldState(),
            layoutType = layoutType,
            currentNavigationItem = navigationItemsLists.first(),
            onNavigationItemClick = {},
        ) {
            MainContentBox(
                navigationSuiteState = rememberNavigationSuiteScaffoldState(),
                layoutType = layoutType,
            ) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center,
                ) {
                    Text(
                        text = "MainContentBox Preview",
                        style = MaterialTheme.typography.bodyLarge,
                    )
                }
            }
        }
    }
}
