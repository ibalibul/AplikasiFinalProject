package com.iqbal.aplikasifinalproject.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore

class UserPrefs(private val context : Context) {

    val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

    val EMAIL = stringPreferencesKey("email")
    val PASSWORD = intPreferencesKey("password")


//    Save Data Store
    suspend fun saveData(email : String, password : Int){
        context.dataStore.edit {
            it[EMAIL] = email
            it[PASSWORD] = password

        }
    }
}