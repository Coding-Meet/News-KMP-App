package utils

import android.content.Intent
import com.coding.meet.newsapp.getActivity
import com.coding.meet.newsapp.getContext
import java.util.UUID


actual fun shareLink(url: String) {
    val sendIntent = Intent(Intent.ACTION_SEND).apply {
        putExtra(Intent.EXTRA_TEXT, url)
        type = "text/plain"
    }
    val shareIntent = Intent.createChooser(sendIntent, "Share Link")
    getContext()?.let {
        getActivity()?.startActivity( shareIntent)
    }
}

actual fun randomUUIDStr(): String {
    return UUID.randomUUID().toString()
}

actual fun getType(): Type {
    return Type.Mobile
}