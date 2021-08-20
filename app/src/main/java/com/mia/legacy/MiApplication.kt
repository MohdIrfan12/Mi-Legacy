package com.mia.legacy

import android.app.Application
import com.mia.legacy.common.dependencyInjection.CompositionRoot
import com.mia.legacy.usecase.config.MovieConfig

/**
 * Created by Mohd Irfan on 31/12/20.
 *
 */
class MiApplication : Application() {

    private lateinit var mCompositionRoot: CompositionRoot
    private var imageBasePath: String? = null

    override fun onCreate() {
        super.onCreate()
        mCompositionRoot = CompositionRoot()
    }

    fun getCompositionRoot(): CompositionRoot {
        return mCompositionRoot;
    }

    fun setMovieConfig(config: MovieConfig) {
         val path = config?.images?.base_url + config?.images?.poster_sizes?.last()
        this.imageBasePath = path;
    }

    fun getImageBasePath(): String? {
        return imageBasePath
    }
}