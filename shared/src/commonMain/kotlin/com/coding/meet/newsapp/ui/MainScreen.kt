package com.coding.meet.newsapp.ui

import androidx.compose.material3.adaptive.currentWindowAdaptiveInfoV2
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteType
import androidx.compose.material3.adaptive.navigationsuite.rememberNavigationSuiteScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.coding.meet.newsapp.ui.navigation.CustomNavigationSuiteScaffoldLayout
import com.coding.meet.newsapp.ui.navigation.NavGraph
import com.coding.meet.newsapp.ui.navigation.NavigationItem
import com.coding.meet.newsapp.ui.navigation.customNavigationSuiteType

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()

    val currentNavigationItem by remember(navBackStackEntry) {
        derivedStateOf {
            NavigationItem.entries.find {
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
        onNavigationItemClick = { navItem ->
            navController.navigate(navItem.route) {
                navController.graph.startDestinationRoute?.let { start ->
                    popUpTo(start) { saveState = true }
                }
                launchSingleTop = true
                restoreState = true
            }
        },
    ) {
        NavGraph(
            navController = navController,
        )
    }
}

