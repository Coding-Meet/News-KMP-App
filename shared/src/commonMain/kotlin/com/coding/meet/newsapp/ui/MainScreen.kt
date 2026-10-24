package com.coding.meet.newsapp.ui

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffoldDefaults
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffoldValue
import androidx.compose.material3.adaptive.navigationsuite.rememberNavigationSuiteScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.coding.meet.newsapp.designsystem.component.navigation.MainContentBox
import com.coding.meet.newsapp.designsystem.component.navigation.SystemNavigationSuiteScaffold
import com.coding.meet.newsapp.ui.navigation.NavigationItem
import com.coding.meet.newsapp.ui.navigation.graphs.NavGraph
import com.coding.meet.newsapp.utils.navigationItemsLists

@Composable
fun MainScreen() {
    val rootNavController = rememberNavController()
    val navBackStackEntry by rootNavController.currentBackStackEntryAsState()

    val currentNavigationItem by remember(navBackStackEntry) {
        derivedStateOf {
            navigationItemsLists.find {
                it.route::class.qualifiedName == navBackStackEntry?.destination?.route
            }
        }
    }

    val windowAdaptiveInfo = currentWindowAdaptiveInfo()
    val layoutType = NavigationSuiteScaffoldDefaults.calculateFromAdaptiveInfo(windowAdaptiveInfo)
    val navigationSuiteState = rememberNavigationSuiteScaffoldState(
        initialValue = NavigationSuiteScaffoldValue.Hidden
    )

    LaunchedEffect(currentNavigationItem) {
        if (currentNavigationItem != null) {
            navigationSuiteState.show()
        } else {
            navigationSuiteState.hide()
        }
    }

    SystemNavigationSuiteScaffold(
        navigationSuiteState = navigationSuiteState,
        layoutType = layoutType,
        currentNavigationItem = currentNavigationItem,
        onNavigationItemClick = { navItem -> navigateTo(rootNavController, navItem) },
    ) {
        MainContentBox(
            navigationSuiteState = navigationSuiteState,
            layoutType = layoutType,
        ) {
            NavGraph(
                rootNavController = rootNavController,
                innerPadding = PaddingValues(),
            )
        }
    }
}

private fun navigateTo(navController: NavHostController, item: NavigationItem) {
    navController.navigate(item.route) {
        navController.graph.startDestinationRoute?.let { start ->
            popUpTo(start) { saveState = true }
        }
        launchSingleTop = true
        restoreState = true
    }
}
