package data.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.coding.meet.newsapp.getContext
import utils.dataStoreFileName
import java.io.File

actual fun dataStorePreferences(): DataStore<Preferences> {
    return createDataStoreWithDefaults(
        path = {
            File(getContext()!!.filesDir, "datastore/$dataStoreFileName").path
        })
}