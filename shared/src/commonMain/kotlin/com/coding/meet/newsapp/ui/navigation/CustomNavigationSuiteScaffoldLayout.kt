package com.coding.meet.newsapp.ui.navigation

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.MenuOpen
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationItemIconPosition
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.PermanentDrawerSheet
import androidx.compose.material3.ShortNavigationBar
import androidx.compose.material3.ShortNavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.material3.WideNavigationRail
import androidx.compose.material3.WideNavigationRailItem
import androidx.compose.material3.WideNavigationRailValue
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfoV2
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteColors
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteDefaults
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffoldLayout
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffoldState
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteType
import androidx.compose.material3.adaptive.navigationsuite.rememberNavigationSuiteScaffoldState
import androidx.compose.material3.rememberWideNavigationRailState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.input.pointer.pointerHoverIcon
import androidx.compose.ui.unit.dp
import com.coding.meet.newsapp.ui.designsystem.preview.AllAdaptivePreviews
import com.coding.meet.newsapp.ui.designsystem.theme.NewsAppTheme
import com.coding.meet.newsapp.utils.Theme
import kotlinx.coroutines.launch
import news_kmp_app.shared.generated.resources.Res
import news_kmp_app.shared.generated.resources.collapse_rail
import news_kmp_app.shared.generated.resources.expand_rail
import org.jetbrains.compose.resources.stringResource

fun Modifier.handCursor(): Modifier = this.pointerHoverIcon(PointerIcon.Hand)

/**
 * Custom adaptive navigation suite scaffold layout supporting short navigation bar,
 * wide navigation rail (collapsed and expanded), navigation rail, and permanent navigation drawer.
 */
@Composable
fun CustomNavigationSuiteScaffoldLayout(
    navigationSuiteState: NavigationSuiteScaffoldState,
    layoutType: NavigationSuiteType,
    currentNavigationItem: NavigationItem?,
    onNavigationItemClick: (NavigationItem) -> Unit,
    colors: NavigationSuiteColors = NavigationSuiteDefaults.colors(),
    content: @Composable BoxScope.() -> Unit,
) {
    val scope = rememberCoroutineScope()
    val wideNavigationRailState = rememberWideNavigationRailState()
    LaunchedEffect(layoutType) {
        when (layoutType) {
            NavigationSuiteType.WideNavigationRailExpanded ->
                wideNavigationRailState.expand()

            NavigationSuiteType.WideNavigationRailCollapsed ->
                wideNavigationRailState.collapse()

            else -> Unit
        }
    }
    NavigationSuiteScaffoldLayout(
        state = navigationSuiteState,
        navigationSuite = {
            when (layoutType) {
                NavigationSuiteType.ShortNavigationBarCompact,
                NavigationSuiteType.ShortNavigationBarMedium,
                NavigationSuiteType.NavigationBar -> {
                    ShortNavigationBar(
                        modifier = Modifier,
                        containerColor = colors.shortNavigationBarContainerColor,
                        contentColor = colors.shortNavigationBarContentColor,
                        content = {
                            NavigationItem.entries.forEach { item ->
                                ShortNavigationBarItem(
                                    modifier = Modifier.handCursor(),
                                    iconPosition = if (layoutType == NavigationSuiteType.ShortNavigationBarCompact) NavigationItemIconPosition.Top else NavigationItemIconPosition.Start,
                                    icon = {
                                        Icon(
                                            imageVector = if (currentNavigationItem == item) item.selectedIcon else item.unselectedIcon,
                                            contentDescription = stringResource(item.title),
                                        )
                                    },
                                    label = { Text(stringResource(item.title)) },
                                    selected = currentNavigationItem == item,
                                    onClick = { onNavigationItemClick(item) },
                                )
                            }
                        },
                    )
                }

                NavigationSuiteType.WideNavigationRailCollapsed,
                NavigationSuiteType.WideNavigationRailExpanded -> {
                    WideNavigationRail(
                        state = wideNavigationRailState,
                        modifier = Modifier,
                        header = {
                            IconButton(
                                modifier = Modifier.padding(start = 24.dp).handCursor(),
                                onClick = {
                                    scope.launch {
                                        if (wideNavigationRailState.targetValue == WideNavigationRailValue.Expanded)
                                            wideNavigationRailState.collapse()
                                        else wideNavigationRailState.expand()
                                    }
                                },
                            ) {
                                if (wideNavigationRailState.targetValue == WideNavigationRailValue.Expanded) {
                                    Icon(
                                        Icons.AutoMirrored.Filled.MenuOpen,
                                        contentDescription = stringResource(Res.string.collapse_rail)
                                    )
                                } else {
                                    Icon(
                                        Icons.Filled.Menu,
                                        contentDescription = stringResource(Res.string.expand_rail)
                                    )
                                }
                            }
                        },
                        colors = colors.wideNavigationRailColors,
                        content = {
                            NavigationItem.entries.forEach { item ->
                                WideNavigationRailItem(
                                    modifier = Modifier.padding(
                                        if (wideNavigationRailState.targetValue == WideNavigationRailValue.Expanded) 5.dp else 0.dp
                                    ).handCursor(),
                                    iconPosition = if (wideNavigationRailState.targetValue == WideNavigationRailValue.Expanded) {
                                        NavigationItemIconPosition.Start
                                    } else {
                                        NavigationItemIconPosition.Top
                                    },
                                    icon = {
                                        Icon(
                                            imageVector = if (currentNavigationItem == item) item.selectedIcon else item.unselectedIcon,
                                            contentDescription = stringResource(item.title),
                                        )
                                    },
                                    label = { Text(stringResource(item.title)) },
                                    selected = currentNavigationItem == item,
                                    onClick = { onNavigationItemClick(item) },
                                    railExpanded = wideNavigationRailState.targetValue == WideNavigationRailValue.Expanded,
                                )
                            }
                        },
                    )
                }

                NavigationSuiteType.NavigationRail -> {
                    NavigationRail(
                        modifier = Modifier,
                        header = { },
                        containerColor = colors.navigationRailContainerColor,
                        contentColor = colors.navigationRailContentColor,
                    ) {
                        Spacer(Modifier.weight(1f))
                        NavigationItem.entries.forEach { item ->
                            NavigationRailItem(
                                modifier = Modifier.handCursor(),
                                icon = {
                                    Icon(
                                        imageVector = if (currentNavigationItem == item) item.selectedIcon else item.unselectedIcon,
                                        contentDescription = stringResource(item.title),
                                    )
                                },
                                label = { Text(stringResource(item.title)) },
                                selected = currentNavigationItem == item,
                                onClick = { onNavigationItemClick(item) },
                            )
                        }
                        Spacer(Modifier.weight(1f))
                    }
                }

                NavigationSuiteType.NavigationDrawer -> {
                    PermanentDrawerSheet(
                        modifier = Modifier,
                        drawerContainerColor = colors.navigationDrawerContainerColor,
                        drawerContentColor = colors.navigationDrawerContentColor,
                    ) {
                        NavigationItem.entries.forEach { item ->
                            NavigationDrawerItem(
                                modifier = Modifier.padding(vertical = 5.dp, horizontal = 10.dp)
                                    .handCursor(),
                                icon = {
                                    Icon(
                                        imageVector = if (currentNavigationItem == item) item.selectedIcon else item.unselectedIcon,
                                        contentDescription = stringResource(item.title),
                                    )
                                },
                                label = { Text(stringResource(item.title)) },
                                selected = currentNavigationItem == item,
                                onClick = { onNavigationItemClick(item) },
                            )
                        }
                    }
                }
            }
        },
        layoutType = layoutType,
        content = {
            MainContentBox(
                navigationSuiteState = navigationSuiteState,
                layoutType = layoutType,
                content = content
            )
        }
    )
}

@AllAdaptivePreviews
@Composable
private fun CustomNavigationSuiteScaffoldPreview() {
    NewsAppTheme(
        appTheme = Theme.SYSTEM_DEFAULT.name,
        darkTheme = isSystemInDarkTheme(),
    ) {
        val windowAdaptiveInfo = currentWindowAdaptiveInfoV2()
        val layoutType = customNavigationSuiteType(windowAdaptiveInfo)
        CustomNavigationSuiteScaffoldLayout(
            navigationSuiteState = rememberNavigationSuiteScaffoldState(),
            layoutType = layoutType,
            currentNavigationItem = NavigationItem.Headlines,
            onNavigationItemClick = {},
        ) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center,
            ) {
                Text(
                    text = "Custom Navigation Preview",
                    style = MaterialTheme.typography.bodyLarge,
                )
            }
        }
    }
}
