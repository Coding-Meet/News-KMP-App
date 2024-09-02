package utils

import android.app.Activity
import android.app.Application
import android.content.Intent
import androidx.room.Room
import androidx.room.RoomDatabase
import data.database.NewsDatabase
import org.koin.mp.KoinPlatform
import java.util.UUID


actual fun shareLink(url: String) {
    val sendIntent = Intent(Intent.ACTION_SEND).apply {
        putExtra(Intent.EXTRA_TEXT, url)
        type = "text/plain"
    }
    val shareIntent = Intent.createChooser(sendIntent, "Share Link")
    activityProvider.invoke().startActivity(shareIntent)
}
private var activityProvider : () -> Activity = {
    throw IllegalArgumentException("Error")
}

fun setActivityProvider(provider :() -> Activity){
    activityProvider = provider
}
actual fun randomUUIDStr(): String {
    return UUID.randomUUID().toString()
}

actual fun getType(): Type {
    return Type.Mobile
}

actual fun getDatabaseBuilder(): RoomDatabase.Builder<NewsDatabase> {
    val appContext = KoinPlatform.getKoin().get<Application>()
    val dbFile = appContext.getDatabasePath(DB_Name)
    return Room.databaseBuilder<NewsDatabase>(
        context = appContext,
        name = dbFile.absolutePath
    )
}