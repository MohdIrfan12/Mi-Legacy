package com.mia.legacy.ui

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith


/**
 * Created by Mohd Irfan on 18/8/21.
 */
@RunWith(AndroidJUnit4::class)
class LauncherActivityTest {


    @Rule
    private val rule = ActivityTestRule<LauncherActivity>(LauncherActivity::class.java)


    // region constants ----------------------------------------------------------------------------
    // endregion constants -------------------------------------------------------------------------
    // region helper fields ------------------------------------------------------------------------
    // endregion helper fields ---------------------------------------------------------------------

    @Before
    @Throws(Exception::class)
    fun setup() {
           scenario = rule
    }

    @After
    @Throws(Exception::class)
    fun tearDown() {
    } // region helper methods -----------------------------------------------------------------------
    // endregion helper methods --------------------------------------------------------------------
    // region helper classes -----------------------------------------------------------------------
    // endregion helper classes --------------------------------------------------------------------
}