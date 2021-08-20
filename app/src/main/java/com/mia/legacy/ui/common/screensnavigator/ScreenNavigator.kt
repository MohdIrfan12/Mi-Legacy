package com.mia.legacy.ui.common.screensnavigator

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import com.mia.legacy.R
import com.mia.legacy.ui.home.HomeActivity

/**
 * Created by Mohd Irfan on 31/12/20.
 *
 */
class ScreenNavigator(private val mActivity: Activity) {

    private fun navigate(navController: NavController?, destinationId: Int, bundle: Bundle?, navOptions: NavOptions) {
        navController?.navigate(destinationId, bundle, navOptions)
    }

    private fun getNavOptionsBuilder(): NavOptions.Builder {
        return NavOptions.Builder()
            .setEnterAnim(R.anim.slide_in_right)
            .setExitAnim(R.anim.slide_out_left)
            .setPopEnterAnim(R.anim.slide_in_left)
            .setPopExitAnim(R.anim.slide_out_right)
    }

    fun splashToUserInfoNavigation(navController: NavController ) {
        navigate(navController, R.id.action_splashFragment_to_userInfoFragment, null, getNavOptionsBuilder().build())
    }

    fun navigateOntoHomeScreen() {
         val intent = Intent(mActivity,HomeActivity::class.java)
        mActivity.startActivity(intent)
    }

    fun userInfoToUploadProfilePicFragmentNavigation(navController: NavController) {
        navigate(navController, R.id.action_userInfoFragment_to_uploadProfilePicFragment, null, getNavOptionsBuilder().build())
    }

    fun userInfoToTellUsAboutYouFragmentNavigation(navController: NavController) {
        navigate(navController, R.id.action_userInfoFragment_to_tellUsAboutYouFragment, null, getNavOptionsBuilder().build())
    }

    fun uploadProfilePicToTellUsAboutYouFragmentNavigation(findNavController: NavController) {
        navigate(findNavController,  R.id.action_uploadProfilePicFragment_to_tellUsAboutYouFragment, null, getNavOptionsBuilder().build())
    }
}