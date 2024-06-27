import androidx.compose.runtime.Composable
import androidx.room.RoomDatabase
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import data.database.NewsDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import org.jetbrains.compose.ui.tooling.preview.Preview
import theme.NewsAppTheme
import ui.navigation.graphs.RootNavGraph

@Composable
@Preview
fun App(databaseBuilder: RoomDatabase.Builder<NewsDatabase>) {
    val newsDatabase =  getRoomDatabase(databaseBuilder)
    NewsAppTheme(true) {
        RootNavGraph(newsDatabase)
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