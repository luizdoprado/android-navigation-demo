package br.com.pradoeduardoluiz.androidnavigationdemo

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import br.com.pradoeduardoluiz.androidnavigationdemo.common.navigation.DeeplinkHandler
import br.com.pradoeduardoluiz.androidnavigationdemo.common.navigation.DeeplinkResolver
import br.com.pradoeduardoluiz.androidnavigationdemo.common.navigation.Navigator
import br.com.pradoeduardoluiz.androidnavigationdemo.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val navigator: Navigator by inject()
    private val deeplinkResolver: DeeplinkResolver by inject()
    private val deeplinkHandler: DeeplinkHandler by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home,
                R.id.navigation_dashboard,
                R.id.navigation_notifications
            )
        )
        navigator.bind(navController, lifecycleScope = lifecycleScope)
        navView.setupWithNavController(navController)
        navView.setOnItemSelectedListener {
            NavigationUI.onNavDestinationSelected(it, navController)
            navController.popBackStack(it.itemId, inclusive = false, saveState = false)
            true
        }
        navController.addOnDestinationChangedListener { _, destination, _ ->
            binding.navView.isVisible =
                appBarConfiguration.topLevelDestinations.contains(destination.id)
        }

        handleDeeplinkIntent(intent)
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        handleDeeplinkIntent(intent)
    }

    /**
     * Processes an incoming intent that may carry an external URL.
     * The URL is resolved to an internal destination via [DeeplinkResolver]
     * and navigation is triggered via [DeeplinkHandler].
     */
    private fun handleDeeplinkIntent(intent: Intent) {
        val url = intent.data?.toString() ?: return
        val destination = deeplinkResolver.resolve(url) ?: return
        deeplinkHandler.handle(destination)
    }
}
