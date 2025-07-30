package br.com.pradoeduardoluiz.androidnavigationdemo

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavType
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.compose.DialogNavigator
import androidx.navigation.compose.composable
import androidx.navigation.createGraph
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.fragment
import androidx.navigation.ui.setupWithNavController
import br.com.pradoeduardoluiz.androidnavigationdemo.common.navigation.route.AppRoute
import br.com.pradoeduardoluiz.androidnavigationdemo.common.navigation.NavigationNode
import br.com.pradoeduardoluiz.androidnavigationdemo.common.navigation.Navigator
import br.com.pradoeduardoluiz.androidnavigationdemo.databinding.ActivityMainBinding
import br.com.pradoeduardoluiz.androidnavigationdemo.ui.dashboard.DashboardFragment
import br.com.pradoeduardoluiz.androidnavigationdemo.ui.details.DetailsFragment
import br.com.pradoeduardoluiz.androidnavigationdemo.ui.home.HomeFragment
import br.com.pradoeduardoluiz.androidnavigationdemo.ui.notifications.NotificationsFragment
import br.com.pradoeduardoluiz.androidnavigationdemo.ui.profile.ProfileFragment
import com.example.compose.ComposeScreen
import com.google.android.material.bottomnavigation.BottomNavigationView
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val navigator: Navigator by inject()
    private val navigationNodes: Set<NavigationNode> by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController

        navController.navigatorProvider.addNavigator(ComposeNavigator())
        navController.navigatorProvider.addNavigator(DialogNavigator())
        navController.graph = navController.createGraph(
            startDestination = AppRoute.Home
        ) {
            fragment<HomeFragment>(AppRoute.Home.route) {}
            fragment<ProfileFragment, AppRoute.Profile> {}
            fragment<DashboardFragment, AppRoute.Dashboard> {}
            fragment<DetailsFragment, AppRoute.Details> {
                argument("details") {
                    type = NavType.StringType
                    nullable = false
                }
            }
            fragment<NotificationsFragment, AppRoute.Notifications>() {}
            navigationNodes.forEach { navNode ->
                navNode.addNode(this)
            }
//            fragment<LoginFragment, AppRoute.Login>() {}
//            fragment<RegistrationFragment, AppRoute.Registration> {}
        }

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = setOf<String>(
            AppRoute.Home.toString(),
            AppRoute.Dashboard.toString(),
            AppRoute.Notifications.toString()
        )

        navigator.bind(navController, lifecycleScope = lifecycleScope)
        navView.setupWithNavController(navController)
        navView.setOnItemSelectedListener { menuItem ->
            val destination = when (menuItem.itemId) {
                R.id.navigation_home -> AppRoute.Home
                R.id.navigation_dashboard -> AppRoute.Dashboard
                R.id.navigation_notifications -> AppRoute.Notifications
                else -> return@setOnItemSelectedListener false
            }

            navController.navigate(destination)
            true
        }
        navController.addOnDestinationChangedListener { controller, destination, bundle ->
            val route = destination.route?.substringAfterLast(".")
            Log.d("TAG", "onCreate: $route")
            Log.d("TAG", "onCreate: ${AppRoute.Home}")
            binding.navView.isVisible = appBarConfiguration.contains(route)
        }
    }
}
