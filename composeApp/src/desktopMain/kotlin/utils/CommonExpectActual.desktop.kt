package utils

import java.awt.Toolkit
import java.awt.datatransfer.StringSelection
import java.util.UUID

actual fun shareLink(url: String) {
    val clipboard = Toolkit.getDefaultToolkit().systemClipboard
    clipboard.setContents(StringSelection(url), null)
}

actual fun randomUUIDStr(): String {
   return UUID.randomUUID().toString()
}