package ui.navigation


object Graph {
    const val MainScreenGraph = "mainScreenGraph"
}

sealed class MainRouteScreen(var route: String) {

    object Headline : MainRouteScreen("headline")
    object Search : MainRouteScreen("search")
    object Bookmark : MainRouteScreen("bookmark")
}
sealed class NewsRouteScreen(var route: String) {
    object NewsDetail : NewsRouteScreen("newsDetail")
}

sealed class SettingRouteScreen(var route: String) {
    object SettingDetail : SettingRouteScreen("settingDetail")
}