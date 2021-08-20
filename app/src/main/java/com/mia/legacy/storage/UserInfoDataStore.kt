package com.mia.legacy.storage

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.mia.legacy.usecase.UserData
import kotlinx.coroutines.flow.map

/**
 * Created by Mohd Irfan on 13/8/21.
 */
private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "user_ifo")

open class UserInfoDataStore(private val context: Context) {


    companion object {
        private val KEY_USER_NAME = stringPreferencesKey("userName")
        private val KEY_PASSWORD = stringPreferencesKey("password")
    }

  open  suspend fun saveUserName(userName: String) {
        context.dataStore.edit {
            it[KEY_USER_NAME] = userName
        }
    }

   open suspend fun savePasswordName(password: String) {
        context.dataStore.edit {
            it[KEY_PASSWORD] = password
        }
    }

    val getUserName = context.dataStore.data.map { it[KEY_USER_NAME] ?: "" }

    val getPassWord = context.dataStore.data.map { it[KEY_PASSWORD] ?: "" }


   open val getData = context.dataStore.data.map {
        UserData(it[KEY_USER_NAME] ?: "", it[KEY_PASSWORD] ?: "")
    }
}