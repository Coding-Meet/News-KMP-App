package com.coding.meet.newsapp.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.compose.ui.unit.dp
import androidx.room.Room
import androidx.room.RoomDatabase
import com.coding.meet.newsapp.data.database.NewsDatabase
import java.awt.Toolkit
import java.awt.datatransfer.StringSelection
import java.io.File
import java.util.*

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

@OptIn(ExperimentalComposeUiApi::class)
@Composable
actual fun getScreenSize(): Size {
    val configuration = LocalWindowInfo.current
    val screenHeightDp = configuration.containerSize.height.dp
    val screenWidthDP = configuration.containerSize.width.dp
    return Size(width = screenWidthDP, height = screenHeightDp)
}

actual fun getDatabaseBuilder(): RoomDatabase.Builder<NewsDatabase> {
    val dbFile = File(System.getProperty("java.io.tmpdir"), DB_Name)
    return Room.databaseBuilder<NewsDatabase>(
        name = dbFile.absolutePath,
    )
}