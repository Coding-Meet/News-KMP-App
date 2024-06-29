import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.window.*
import androidx.room.Room
import androidx.room.RoomDatabase
import data.database.NewsDatabase
import news_kmp_app.composeapp.generated.resources.*
import utils.DB_Name
import java.awt.Dimension
import java.io.File

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication, state = WindowState(
            position = WindowPosition(Alignment.Center)
        ),   title = "News KMP APP",
        icon = org.jetbrains.compose.resources.painterResource(Res.drawable.logo)
    ) {
        window.minimumSize = Dimension(1280, 768)
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