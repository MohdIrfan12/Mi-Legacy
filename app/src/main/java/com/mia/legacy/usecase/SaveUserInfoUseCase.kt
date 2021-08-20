package com.mia.legacy.usecase

import com.mia.legacy.storage.UserInfoDataStore

/**
 * Created by Mohd Irfan on 13/8/21.
 */
open class SaveUserInfoUseCase(private val mUserInfoStore: UserInfoDataStore) {

    open suspend fun saveUserInfo(userName: String, password: String) {
        mUserInfoStore.saveUserName(userName)
        mUserInfoStore.savePasswordName(password)
    }
}