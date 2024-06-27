import androidx.compose.runtime.remember
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.room.Room
import androidx.room.RoomDatabase
import data.database.NewsDatabase
import utils.DB_Name
import java.io.File

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "News app",
    ) {
        val database = remember { getDatabaseBuilder() }

        App(database)
    }
}
fun getDatabaseBuilder(): RoomDatabase.Builder<NewsDatabase> {
    val dbFile = File(System.getProperty("java.io.tmpdir"), DB_Name)
    return Room.databaseBuilder<NewsDatabase>(
        name = dbFile.absolutePath,
    )
}