package com.mia.mvvvmcarchitecture.ui.common.controllers

/**
 * Created by Mohd Irfan
 * on 01/01/21.
 */
interface BackPressDispatcher {
    fun registerListener(listener: BackPressListener)
    fun unRegisterListener(listener: BackPressListener)
    fun onDispatchBackPressed()
}