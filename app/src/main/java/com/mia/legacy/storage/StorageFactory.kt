package com.mia.legacy.storage

import android.content.Context

/**
 * Created by Mohd Irfan on 13/8/21.
 */
class StorageFactory(private val context: Context) {

    fun getUserInfoDataStore():UserInfoDataStore{
        return UserInfoDataStore(context);
    }

    fun getUserDataProtoStore(): UserDataProtoStore {
        return UserDataProtoStore(context)
    }
}