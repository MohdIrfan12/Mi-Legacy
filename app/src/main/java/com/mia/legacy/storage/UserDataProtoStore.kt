package com.mia.legacy.storage

import android.content.Context
import androidx.datastore.core.CorruptionException
import androidx.datastore.core.DataStore
import androidx.datastore.core.Serializer
import androidx.datastore.dataStore
import androidx.datastore.preferences.protobuf.InvalidProtocolBufferException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.io.InputStream
import java.io.OutputStream

val Context.userInfoDataStore: DataStore<MiProto.UserDataSchema> by dataStore(
    fileName = "userdata.pb",
    serializer = object : Serializer<MiProto.UserDataSchema> {
        override val defaultValue: MiProto.UserDataSchema = MiProto.UserDataSchema.getDefaultInstance()

        override suspend fun writeTo(t: MiProto.UserDataSchema, output: OutputStream) =
            t.writeTo(output)

        override suspend fun readFrom(input: InputStream): MiProto.UserDataSchema {
            try {
                return MiProto.UserDataSchema.parseFrom(input)
            } catch (exception: InvalidProtocolBufferException) {
                throw CorruptionException("Cannot read proto.", exception)
            }
        }
    }
)


/**
 * Created by Mohd Irfan on 13/8/21.
 */
open class UserDataProtoStore(private val context: Context) {

    suspend fun saveUserData(
        userName: String?,
        password: String?,
        profession: String?,
        summary: String?
    ) {
        context.userInfoDataStore.updateData {
            it.toBuilder()
                .setUserName(userName?:it.userName)
                .setPassword(password?:it.password)
                .setProfession(profession?:it.profession)
                .setSummary(summary?:it.summary).build()
        }
    }

   open val getUserData: Flow<MiProto.UserDataSchema> = context.userInfoDataStore.data.map { it }
}