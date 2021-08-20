package com.mia.legacy.usecase

import com.mia.legacy.storage.UserInfoDataStore
import kotlinx.coroutines.flow.Flow

/**
 * Created by Mohd Irfan on 13/8/21.
 */
open class FetchUserInfoUseCase(private val mUserInfoStore: UserInfoDataStore) {

    open fun fetchUserInfo(): Flow<UserData> {
//        return mUserInfoStore.getUserName
//            .combine(mUserInfoStore.getPassWord) { userName, pass -> UserData(userName, pass) }
         return mUserInfoStore.getData
    }
}