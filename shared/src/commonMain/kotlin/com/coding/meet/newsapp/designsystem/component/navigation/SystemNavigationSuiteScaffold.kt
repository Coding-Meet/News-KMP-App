package com.coding.meet.newsapp.designsystem.component.navigation

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfoV2
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteDefaults
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffold
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffoldDefaults
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffoldState
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScope
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteType
import androidx.compose.material3.adaptive.navigationsuite.rememberNavigationSuiteScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.coding.meet.newsapp.designsystem.preview.AllAdaptivePreviews
import com.coding.meet.newsapp.designsystem.theme.NewsAppTheme
import com.coding.meet.newsapp.ui.navigation.NavigationItem
import com.coding.meet.newsapp.ui.navigation.customNavigationSuiteType
import com.coding.meet.newsapp.utils.Theme
import com.coding.meet.newsapp.utils.navigationItemsLists
import org.jetbrains.compose.resources.stringResource

/**
 * A custom wrapper for [NavigationSuiteScaffold] that adaptively switches between
 * a Bottom Navigation Bar, Navigation Rail, or Navigation Drawer based on the [layoutType].
 *
 * @param navigationSuiteState Controls the visibility (show/hide) of the navigation component.
 * @param layoutType The current layout strategy (e.g., BottomBar for mobile, Rail for tablets).
 * @param currentNavigationItem The currently active [NavigationItem], used to highlight the selection.
 * @param onNavigationItemClick Callback triggered when a navigation item is pressed.
 * @param content The main UI content to be displayed alongside the navigation suite.
 */
@Composable
fun SystemNavigationSuiteScaffold(
    navigationSuiteState: NavigationSuiteScaffoldState,
    layoutType: NavigationSuiteType,
    currentNavigationItem: NavigationItem?,
    onNavigationItemClick: (NavigationItem) -> Unit,
    content: @Composable () -> Unit
) {
    NavigationSuiteScaffold(
        state = navigationSuiteState,
        navigationSuiteItems = {
            systemNavBar(
                currentNavigationItem = currentNavigationItem,
                onNavigationItemClick = onNavigationItemClick
            )
        },
        layoutType = layoutType,
        navigationSuiteColors = NavigationSuiteDefaults.colors(),
        containerColor = MaterialTheme.colorScheme.background,
        contentColor = MaterialTheme.colorScheme.onBackground,
        content = content
    )
}

/**
 * Extension function for [NavigationSuiteScope] to define the items
 * within the navigation bar/rail.
 */
fun NavigationSuiteScope.systemNavBar(
    currentNavigationItem: NavigationItem?,
    onNavigationItemClick: (NavigationItem) -> Unit
) {
    navigationItemsLists.forEach { navItem ->
        val selected = currentNavigationItem == navItem
        item(
            icon = {
                Icon(
                    imageVector = navItem.icon,
                    contentDescription = stringResource(navItem.title)
                )
            },
            label = { Text(stringResource(navItem.title)) },
            selected = selected,
            onClick = { onNavigationItemClick(navItem) }
        )
    }
}

@AllAdaptivePreviews
@Composable
private fun SystemNavigationPreview() {
    NewsAppTheme(
        appTheme = Theme.SYSTEM_DEFAULT.name,
        darkTheme = isSystemInDarkTheme(),
    ) {
        val windowAdaptiveInfo = currentWindowAdaptiveInfoV2()
        val inbuiltLayoutType = customNavigationSuiteType(windowAdaptiveInfo)
        SystemNavigationSuiteScaffold(
            navigationSuiteState = rememberNavigationSuiteScaffoldState(),
            layoutType = inbuiltLayoutType,
            currentNavigationItem = navigationItemsLists.first(),
            onNavigationItemClick = {}
        ) {
            Scaffold {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(it),
                    contentAlignment = Alignment.Center
                ) {
                    Text("Navigation Preview")
                }
            }
        }
    }
}
