package com.mia.mvvvmcarchitecture.common.observer

import java.util.*
import kotlin.collections.HashSet

/**
 * Created by Mohd Irfan on 29/12/20.
 *
 */
abstract class BaseObservable<ListenerType> {

    private val mListeners: HashSet<ListenerType> = HashSet()

    internal fun addObserver(listener: ListenerType) {
        mListeners.add(listener)
    }

    internal open fun removeObserver(listener: ListenerType) {
        mListeners.remove(listener)
    }

    protected fun getObserver(): Set<ListenerType> {
        return Collections.unmodifiableSet(mListeners)
    }
}