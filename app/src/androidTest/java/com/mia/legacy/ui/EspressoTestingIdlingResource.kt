package com.mia.legacy.ui

import androidx.test.espresso.IdlingResource

import androidx.test.espresso.idling.CountingIdlingResource




/**
 * Created by Mohd Irfan on 22/8/21.
 */
object  EspressoTestingIdlingResource {

    private val RESOURCE = "GLOBAL"

    private val mCountingIdlingResource = CountingIdlingResource(RESOURCE)

    fun increment() {
        mCountingIdlingResource.increment()
    }

    fun decrement() {
        mCountingIdlingResource.decrement()
    }

    fun getIdlingResource(): IdlingResource? {
        return mCountingIdlingResource
    }

}