package utils


import androidx.room.Room
import androidx.room.RoomDatabase
import data.database.NewsDatabase
import data.database.instantiateImpl
import platform.UIKit.*
import platform.Foundation.NSUUID
import platform.Foundation.NSHomeDirectory

actual fun shareLink(url: String) {
    val currentViewController = UIApplication.sharedApplication().keyWindow?.rootViewController
    val activityViewController = UIActivityViewController(listOf(url), null)
    currentViewController?.presentViewController(
        viewControllerToPresent = activityViewController,
        animated = true,
        completion = null
    )
}

actual fun randomUUIDStr(): String {
    return NSUUID().UUIDString()
}

actual fun getType(): Type {
    return Type.Mobile
}

actual fun getDatabaseBuilder(): RoomDatabase.Builder<NewsDatabase> {
    val dbFilePath = NSHomeDirectory() + "/$DB_Name"
    return Room.databaseBuilder<NewsDatabase>(
        name = dbFilePath,
        factory =  { NewsDatabase::class.instantiateImpl() }
    )
}