import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import di.koinViewModel
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.KoinContext
import theme.NewsAppTheme
import ui.navigation.graphs.RootNavGraph
import ui.setting.SettingViewModel
@Composable
@Preview
fun App() {
    KoinContext {
        val settingViewModel = koinViewModel<SettingViewModel>()
        val isDarkModeEnabled by settingViewModel.isDarkModeEnabled.collectAsState(isSystemInDarkTheme())
        val isInit by settingViewModel.isInit.collectAsState(false)
        if (isInit) {
            NewsAppTheme(isDarkModeEnabled) {
                RootNavGraph(settingViewModel)
            }
        }
    }
}