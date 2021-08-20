package com.mia.legacy.usecase

import com.mia.legacy.storage.UserDataProtoStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

/**
 * Created by Mohd Irfan on 13/8/21.
 */
open class FetchUserDataUseCase(private val mUserDataProto: UserDataProtoStore) {

    open fun getUserInfo(): Flow<UserData> {
        return mUserDataProto.getUserData.map {
            UserData(it.userName, it.password, it.profession, it.summary)
        }
    }
}