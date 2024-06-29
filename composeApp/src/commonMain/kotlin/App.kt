import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.room.RoomDatabase
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import data.database.NewsDatabase
import data.datastore.dataStorePreferences
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import org.jetbrains.compose.ui.tooling.preview.Preview
import theme.NewsAppTheme
import ui.navigation.graphs.RootNavGraph
import ui.setting.SettingViewModel
import utils.AppPreferencesImpl

@Composable
@Preview
fun App(databaseBuilder: RoomDatabase.Builder<NewsDatabase>) {
    val newsDatabase = remember {   getRoomDatabase(databaseBuilder)}
    val appPreferencesImpl = remember {  AppPreferencesImpl(dataStorePreferences())}

    val settingViewModel = viewModel { SettingViewModel(appPreferencesImpl,newsDatabase) }
    val isDarkModeEnabled by settingViewModel.isDarkModeEnabled.collectAsState(isSystemInDarkTheme())
    val isInit by settingViewModel.isInit.collectAsState(false)
    if (isInit) {
        NewsAppTheme(isDarkModeEnabled) {
            RootNavGraph(newsDatabase, settingViewModel)
        }
    }
}

fun getRoomDatabase(
    builder: RoomDatabase.Builder<NewsDatabase>
): NewsDatabase {
    return builder
        .setDriver(BundledSQLiteDriver())
        .setQueryCoroutineContext(Dispatchers.IO)
        .build()
}