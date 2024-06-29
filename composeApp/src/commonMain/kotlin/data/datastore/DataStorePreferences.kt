package data.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.SupervisorJob
import okio.Path.Companion.toPath

expect fun dataStorePreferences(): DataStore<Preferences>

fun createDataStoreWithDefaults(
    path: () -> String,
) = PreferenceDataStoreFactory
    .createWithPath(
        scope = CoroutineScope(Dispatchers.IO + SupervisorJob()),
        produceFile = {
            path().toPath()
        }
    )




