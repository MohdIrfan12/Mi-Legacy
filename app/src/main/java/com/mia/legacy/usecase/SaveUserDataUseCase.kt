package com.mia.legacy.usecase

import com.mia.legacy.storage.UserDataProtoStore

/**
 * Created by Mohd Irfan on 13/8/21.
 */
open class SaveUserDataUseCase(private val mUserDataProto: UserDataProtoStore) {

   open suspend fun saveUserInfo(userData: UserData) {
        mUserDataProto.saveUserData(
            userData.userName,
            userData.password,
            userData.profession,
            userData.summary
        )
    }
}