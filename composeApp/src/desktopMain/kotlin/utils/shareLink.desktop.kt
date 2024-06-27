package utils

import java.awt.Toolkit
import java.awt.datatransfer.StringSelection

actual fun shareLink(url: String) {
    val clipboard = Toolkit.getDefaultToolkit().systemClipboard
    clipboard.setContents(StringSelection(url), null)
}