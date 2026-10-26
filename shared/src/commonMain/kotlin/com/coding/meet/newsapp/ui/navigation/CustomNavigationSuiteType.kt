package com.coding.meet.newsapp.ui.navigation


import androidx.compose.material3.adaptive.WindowAdaptiveInfo
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteType
import androidx.window.core.layout.WindowSizeClass

fun customNavigationSuiteType(
    windowAdaptiveInfo: WindowAdaptiveInfo
): NavigationSuiteType {
    val windowSizeClass = windowAdaptiveInfo.windowSizeClass

    val isMediumWidth =
        windowSizeClass.isWidthAtLeastBreakpoint(WindowSizeClass.WIDTH_DP_MEDIUM_LOWER_BOUND)
    val isExpandedWidth =
        windowSizeClass.isWidthAtLeastBreakpoint(WindowSizeClass.WIDTH_DP_EXPANDED_LOWER_BOUND)

    val isMediumHeight =
        windowSizeClass.isHeightAtLeastBreakpoint(WindowSizeClass.HEIGHT_DP_MEDIUM_LOWER_BOUND)
    val isExpandedHeight =
        windowSizeClass.isHeightAtLeastBreakpoint(WindowSizeClass.HEIGHT_DP_EXPANDED_LOWER_BOUND)

    return when {
        // Expanded Width (Tablets landscape / Desktop)
        isExpandedWidth -> {
            when {
                isExpandedHeight -> NavigationSuiteType.NavigationDrawer
                !isMediumHeight -> NavigationSuiteType.NavigationRail
                else -> NavigationSuiteType.WideNavigationRailCollapsed
            }
        }

        // Medium Width (Large phones / Small tablets portrait)
        isMediumWidth -> {
            when {
                !isMediumHeight -> NavigationSuiteType.NavigationBar
                else -> NavigationSuiteType.ShortNavigationBarMedium
            }
        }

        // Compact Width (Standard phones)
        else -> NavigationSuiteType.ShortNavigationBarCompact
    }
}