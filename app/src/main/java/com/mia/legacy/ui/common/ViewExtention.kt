package com.mia.legacy.ui.common


import android.widget.ImageView
import coil.ImageLoader
import coil.load
import coil.request.CachePolicy
import coil.size.Scale
import coil.transform.CircleCropTransformation
import coil.util.DebugLogger
import com.mia.legacy.R


fun ImageView.loadImage(url: String?) {
    if (url == null || url.trim().isEmpty()) {
        this.load(R.drawable.ic_placeholder)
        return
    }

    this.load(url) {
        crossfade(true)
        memoryCachePolicy(CachePolicy.ENABLED)
        (CachePolicy.ENABLED)
        placeholder(R.drawable.ic_placeholder)
        error(R.drawable.ic_placeholder)
    }
}