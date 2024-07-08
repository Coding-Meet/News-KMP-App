package utils

import androidx.room.Room
import androidx.room.RoomDatabase
import data.database.NewsDatabase
import java.awt.Toolkit
import java.awt.datatransfer.StringSelection
import java.io.File
import java.util.UUID

actual fun shareLink(url: String) {
    val clipboard = Toolkit.getDefaultToolkit().systemClipboard
    clipboard.setContents(StringSelection(url), null)
}

actual fun randomUUIDStr(): String {
    return UUID.randomUUID().toString()
}

actual fun getType(): Type {
    return Type.Desktop
}

actual fun getDatabaseBuilder(): RoomDatabase.Builder<NewsDatabase> {
    val dbFile = File(System.getProperty("java.io.tmpdir"), DB_Name)
    return Room.databaseBuilder<NewsDatabase>(
        name = dbFile.absolutePath,
    )
}