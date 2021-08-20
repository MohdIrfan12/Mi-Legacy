package com.mia.legacy.ui

import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import com.mia.legacy.databinding.ActivityLauncherBinding
import com.mia.legacy.ui.common.binders.BaseActivity

class LauncherActivity : BaseActivity() {

    private lateinit var navController: NavController
    private lateinit var binding: ActivityLauncherBinding
    private val TAG = "LauncherActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("onCreate",TAG)
        binding = ActivityLauncherBinding.inflate(layoutInflater);
        setContentView(binding.root)
//        setToolbar()
        setNavigationWithDefaultActiionBar()
    }

//    private fun setToolbar() {
//        val navHostFragment =
//            supportFragmentManager.findFragmentById(binding.navHostFragmentContainer.id) as NavHostFragment
//        val navController = navHostFragment.navController
//
//        val appBarConfiguration = AppBarConfiguration(navController.graph)
//        binding.toolbar.setupWithNavController(navController, appBarConfiguration)
//        binding.toolbar.setTitleTextColor(ContextCompat.getColor(this,R.color.color_FFFFFF))
//    }

    private fun setNavigationWithDefaultActiionBar() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(binding.navHostFragmentContainer.id) as NavHostFragment
        navController = navHostFragment.navController
        val appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

    override fun onStart() {
        super.onStart()
        Log.d("onStart",TAG)
    }

    override fun onRestart() {
        super.onRestart()
        Log.d("onRestart",TAG)
    }

    override fun onResume() {
        super.onResume()
        Log.d("onResume",TAG)
    }

    override fun onPause() {
        super.onPause()
        Log.d("onPause",TAG)
    }

    override fun onStop() {
        super.onStop()
        Log.d("onStop",TAG)
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("onDestroy",TAG)
    }

    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        super.onSaveInstanceState(outState, outPersistentState)
        Log.d("onSaveInstanceState",TAG)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        Log.d("onRestoreInstanceSt",TAG)
    }
}