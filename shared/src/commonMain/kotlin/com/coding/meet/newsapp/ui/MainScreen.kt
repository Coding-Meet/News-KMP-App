package com.coding.meet.newsapp.ui

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfoV2
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteType
import androidx.compose.material3.adaptive.navigationsuite.rememberNavigationSuiteScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.coding.meet.newsapp.designsystem.component.navigation.CustomNavigationSuiteScaffoldLayout
import com.coding.meet.newsapp.ui.navigation.NavigationItem
import com.coding.meet.newsapp.ui.navigation.customNavigationSuiteType
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

    val windowAdaptiveInfo = currentWindowAdaptiveInfoV2()
    val navigationSuiteState = rememberNavigationSuiteScaffoldState()

    val layoutType = if (currentNavigationItem == null) {
        NavigationSuiteType.None
    } else {
        customNavigationSuiteType(windowAdaptiveInfo)
    }

    CustomNavigationSuiteScaffoldLayout(
        navigationSuiteState = navigationSuiteState,
        layoutType = layoutType,
        currentNavigationItem = currentNavigationItem,
        onNavigationItemClick = { navItem -> navigateTo(rootNavController, navItem) },
    ) {
        NavGraph(
            rootNavController = rootNavController,
            innerPadding = PaddingValues(),
        )
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
