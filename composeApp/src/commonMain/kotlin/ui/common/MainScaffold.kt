package ui.common

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.navigation.NavHostController
import ui.navigation.NewsBottomNavigation
import utils.bottomNavigationItemsList

@Composable
fun MainScaffold(
    rootNavController: NavHostController,
    currentRoute: String?,
    bottomBarVisibility: Boolean,
    content: @Composable (PaddingValues) -> Unit
) {
    Scaffold(
        bottomBar = {
            AnimatedVisibility(
                visible = bottomBarVisibility,
                enter = slideInVertically(
                    // Slide in from the bottom
                    initialOffsetY = { fullHeight -> fullHeight }
                ),
                exit = slideOutVertically(
                    // Slide out to the bottom
                    targetOffsetY = { fullHeight -> fullHeight }
                )
            ) {
                NewsBottomNavigation(items = bottomNavigationItemsList,
                    currentRoute = currentRoute,
                    onItemClick = { currentNavigationItem ->
                        rootNavController.navigate(currentNavigationItem.route) {
                            // Pop up to the start destination of the graph to
                            // avoid building up a large stack of destinations
                            // on the back stack as users select items
                            rootNavController.graph.startDestinationRoute?.let { startDestinationRoute ->
                                // Pop up to the start destination, clearing the back stack
                                popUpTo(startDestinationRoute) {
                                    // Save the state of popped destinations
                                    saveState = true
                                }
                            }

                            // Configure navigation to avoid multiple instances of the same destination
                            launchSingleTop = true

                            // Restore state when re-selecting a previously selected item
                            restoreState = true
                        }
                    })

            }
        }
    ) { innerPadding ->
        content(innerPadding)
    }

}