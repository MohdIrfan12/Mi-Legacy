package com.mia.legacy.usecase

import com.google.gson.Gson
import com.mia.legacy.networking.EndpointFactory
import com.mia.legacy.storage.StorageFactory
import com.mia.legacy.usecase.Movies.FetchHotstarMoviesUseCase
import com.mia.legacy.usecase.Movies.FetchNetflixMoviesUseCase
import com.mia.legacy.usecase.config.FetchMoviesConfigUseCase

/**
 * Created by Mohd Irfan on 07/01/21.
 */
class UsecaseFactory(
    private val endpoint: EndpointFactory,
    private val storageFactory: StorageFactory,
    private val gson: Gson
) {

    fun getSaveUserInfoUseCase(): SaveUserInfoUseCase {
        return SaveUserInfoUseCase(storageFactory.getUserInfoDataStore())
    }

    fun getFetchUserInfoUseCase(): FetchUserInfoUseCase {
        return FetchUserInfoUseCase(storageFactory.getUserInfoDataStore())
    }

    fun getSaveUserDataUseCase(): SaveUserDataUseCase {
        return SaveUserDataUseCase(storageFactory.getUserDataProtoStore())
    }

    fun getFetchUserDataUseCase(): FetchUserDataUseCase {
        return FetchUserDataUseCase(storageFactory.getUserDataProtoStore())
    }

    fun getFetchMoviesUseCase(): FetchHotstarMoviesUseCase {
        return FetchHotstarMoviesUseCase(endpoint.getFetchMoviesEndpoint(),gson)
    }

    fun getFetchMoviesConfigUseCase(): FetchMoviesConfigUseCase {
        return FetchMoviesConfigUseCase(endpoint.getMoviesConfigEndpoint(),gson)
    }

    fun getFetchNetflixMoviesUseCase(): FetchNetflixMoviesUseCase {
        return FetchNetflixMoviesUseCase(endpoint.getFetchNetflixMoviesEndpoint(),gson)
    }
}