package utils

import android.content.Context
import android.content.Intent
import androidx.core.content.ContextCompat.startActivity
import com.coding.meet.newsapp.MyApp


actual fun shareLink(url: String) {
    val sendIntent = Intent(Intent.ACTION_SEND).apply {
        putExtra(Intent.EXTRA_TEXT, url)
        type = "text/plain"
    }
    val shareIntent = Intent.createChooser(sendIntent, "Share Link")
    getContext()?.let {
        startActivity(it, shareIntent, null)
    }
}

fun getContext(): Context?{
    return MyApp.instance
}