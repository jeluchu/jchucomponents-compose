package com.jeluchu.prefs.datastore

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.doublePreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.floatPreferencesKey
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class DataStoreHelpers {
    fun <T> savePreference(key: Preferences.Key<T>, value: T) {
        CoroutineScope(Dispatchers.IO).launch {
            dataStore?.edit { preferences ->
                preferences[key] = value
            }
        }
    }

    fun savePreference(key: Preferences.Key<Int>, value: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            dataStore?.edit { preferences ->
                preferences[key] = value
            }
        }
    }

    fun savePreference(key: Preferences.Key<Long>, value: Long) {
        CoroutineScope(Dispatchers.IO).launch {
            dataStore?.edit { preferences ->
                preferences[key] = value
            }
        }
    }

    fun savePreference(key: Preferences.Key<Float>, value: Float) {
        CoroutineScope(Dispatchers.IO).launch {
            dataStore?.edit { preferences ->
                preferences[key] = value
            }
        }
    }

    fun savePreference(key: Preferences.Key<String>, value: String) {
        CoroutineScope(Dispatchers.IO).launch {
            dataStore?.edit { preferences ->
                preferences[key] = value
            }
        }
    }

    fun savePreference(key: Preferences.Key<Boolean>, value: Boolean) {
        CoroutineScope(Dispatchers.IO).launch {
            dataStore?.edit { preferences ->
                preferences[key] = value
            }
        }
    }

    fun <T> getPreference(
        key: Preferences.Key<T>
    ) = runBlocking {
        dataStore?.data?.first()?.toPreferences()?.get(key)
    }

    fun getPreference(
        key: Preferences.Key<Int>,
        default: Int = 0
    ) = runBlocking {
        dataStore?.data?.first()?.toPreferences()?.get(key) ?: default
    }

    fun getPreference(
        key: Preferences.Key<Long>,
        default: Long = 0L
    ) = runBlocking {
        dataStore?.data?.first()?.toPreferences()?.get(key) ?: default
    }

    fun getPreference(
        key: Preferences.Key<Float>,
        default: Float = 0F
    ) = runBlocking {
        dataStore?.data?.first()?.toPreferences()?.get(key) ?: default
    }

    fun getPreference(
        key: Preferences.Key<String>,
        default: String = ""
    ) = runBlocking {
        dataStore?.data?.first()?.toPreferences()?.get(key) ?: default
    }

    fun getPreference(
        key: Preferences.Key<Boolean>,
        default: Boolean = false
    ) = runBlocking {
        dataStore?.data?.first()?.toPreferences()?.get(key) ?: default
    }

    fun clearAllPreferences() = runBlocking {
        dataStore?.edit { preferences -> preferences.clear() }
    }

    companion object {
        var dataStore: DataStore<Preferences>? = null
        val Context.defaultPreferencesDataStore by preferencesDataStore(name = "default")

        fun initDataStore(context: Context) {
            dataStore = context.defaultPreferencesDataStore
        }

        fun intPrefsKey(name: String) = intPreferencesKey(name)
        fun floatPrefsKey(name: String) = floatPreferencesKey(name)
        fun longPrefsKey(name: String) = longPreferencesKey(name)
        fun doublePrefsKey(name: String) = doublePreferencesKey(name)
        fun stringPrefsKey(name: String) = stringPreferencesKey(name)
        fun booleanPrefsKey(name: String) = booleanPreferencesKey(name)

        fun <T> DataStore<Preferences>.getValueSync(
            key: Preferences.Key<T>
        ) = runBlocking { data.first() }[key]

        fun DataStore<Preferences>.getValueSync(
            key: Preferences.Key<Int>,
            default: Int = 0
        ) = runBlocking { data.first() }[key] ?: default

        fun DataStore<Preferences>.getValueSync(
            key: Preferences.Key<Long>,
            default: Long = 0L
        ) = runBlocking { data.first() }[key] ?: default

        fun DataStore<Preferences>.getValueSync(
            key: Preferences.Key<Float>,
            default: Float = 0F
        ) = runBlocking { data.first() }[key] ?: default

        fun DataStore<Preferences>.getValueSync(
            key: Preferences.Key<String>,
            default: String = ""
        ) = runBlocking { data.first() }[key] ?: default

        fun DataStore<Preferences>.getValueSync(
            key: Preferences.Key<Boolean>,
            default: Boolean = false
        ) = runBlocking { data.first() }[key] ?: default

        @Composable
        fun <T> rememberPreference(
            key: Preferences.Key<T>,
            defaultValue: T,
        ): MutableState<T> {
            val coroutineScope = rememberCoroutineScope()
            val context = LocalContext.current
            val state = remember {
                context.defaultPreferencesDataStore.data
                    .map {
                        it[key] ?: defaultValue
                    }
            }.collectAsStateWithLifecycle(initialValue = defaultValue)

            return remember {
                object : MutableState<T> {
                    override var value: T
                        get() = state.value
                        set(value) {
                            coroutineScope.launch {
                                context.defaultPreferencesDataStore.edit {
                                    it[key] = value
                                }
                            }
                        }

                    override fun component1() = value
                    override fun component2(): (T) -> Unit = { value = it }
                }
            }
        }
    }
}