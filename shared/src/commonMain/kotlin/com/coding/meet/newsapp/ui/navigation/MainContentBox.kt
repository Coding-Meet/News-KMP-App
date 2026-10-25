package com.coding.meet.newsapp.ui.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.only
import androidx.compose.material3.DrawerDefaults
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.NavigationRailDefaults
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffoldState
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffoldValue
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteType
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

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