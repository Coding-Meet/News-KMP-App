import androidx.compose.runtime.remember
import androidx.compose.ui.window.ComposeUIViewController
import androidx.room.Room
import androidx.room.RoomDatabase
import data.database.NewsDatabase
import utils.DB_Name

fun MainViewController() = ComposeUIViewController {
    val database = remember {
        getDatabaseBuilder()
    }
    App(database) }

fun getDatabaseBuilder(): RoomDatabase.Builder<NewsDatabase> {
    val dbFilePath = NSHomeDirectory() + "/$DB_Name"
    return Room.databaseBuilder<NewsDatabase>(
        name = dbFilePath,
        factory =  { NewsDatabase::class.instantiateImpl() }
    )
}