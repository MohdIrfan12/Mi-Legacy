package com.mia.legacy.ui.home

import android.app.Service
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.MenuItem
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.*
import com.mia.legacy.databinding.ActivityHomeBinding
import com.mia.legacy.ui.common.binders.BaseActivity

class HomeActivity : BaseActivity() {

    private lateinit var viewmodel: HomeViewModel
    private lateinit var mAppBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val factory = mPresentationCompositionRoot.getViewModelProviderFactory(this)
        viewmodel = ViewModelProvider(this, factory).get(HomeViewModel::class.java)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setDefaultActionBar()
    }

    private fun setDefaultActionBar() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(binding.navHostFragment.id) as NavHostFragment
        val navController = navHostFragment.navController

        mAppBarConfiguration = AppBarConfiguration(navController.graph, binding.drawerLayout)
        setupActionBarWithNavController(navController, mAppBarConfiguration)

        setUpWithNavDrawer(navController)
        setupWithBottomNavigationView(navController)
    }

    private fun setUpWithNavDrawer(navController: NavController) {
        binding?.drawerNavView.setupWithNavController(navController)
    }

    private fun setupWithBottomNavigationView(navController: NavController) {
        binding.bottomNavigationView.setupWithNavController(navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(binding.navHostFragment.id)
        return navController.navigateUp(mAppBarConfiguration) || super.onSupportNavigateUp()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val navController = findNavController(binding.navHostFragment.id)
        return item.onNavDestinationSelected(navController) || super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        if (binding.drawerLayout.isDrawerOpen(binding.drawerNavView)) {
            binding.drawerLayout.closeDrawers()
        } else {
            super.onBackPressed()
        }
    }
}