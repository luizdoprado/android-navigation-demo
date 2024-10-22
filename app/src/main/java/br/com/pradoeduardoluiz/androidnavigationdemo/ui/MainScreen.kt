package br.com.pradoeduardoluiz.androidnavigationdemo.ui

import android.widget.Toast
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import br.com.pradoeduardoluiz.androidnavigationdemo.common.ext.collectNavigationResult
import br.com.pradoeduardoluiz.androidnavigationdemo.login.ui.login.LoginScreen
import br.com.pradoeduardoluiz.androidnavigationdemo.ui.dashboard.DashboardScreen
import br.com.pradoeduardoluiz.androidnavigationdemo.ui.details.DetailsScreen
import br.com.pradoeduardoluiz.androidnavigationdemo.ui.home.HomeScreen
import br.com.pradoeduardoluiz.androidnavigationdemo.ui.notifications.NotificationsScreen
import br.com.pradoeduardoluiz.androidnavigationdemo.ui.profile.ProfileScreen

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = {
            BottomBar(navController)
        }
    ) { innerPadding ->
        NavHost(
            navController,
            startDestination = Route.Home,
            Modifier.padding(innerPadding)
        ) {
            composable<Route.Home> {
                HomeScreen(
                    goToProfile = { navController.navigate(Route.Profile(userId = "USER ID")) }
                )
            }
            composable<Route.Dashboard> { DashboardScreen() }
            composable<Route.Notifications> { NotificationsScreen() }
            composable<Route.Login> { LoginScreen() }
            composable<Route.Profile> { navBackResult ->
                val context = LocalContext.current
                navBackResult.collectNavigationResult<String>("userId") {
                    Toast.makeText(
                        context,
                        "Result From Details : $it",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                ProfileScreen(
                    goToDetails = { userId ->
                        navController.navigate(Route.Details(userId))
                    }
                )
            }
            composable<Route.Details> {
                DetailsScreen(
                    goBackWithArguments = {
                        navController.navigateUp()
                        navController.currentBackStackEntry
                            ?.savedStateHandle
                            ?.set("userId", "New User Id")
                    },
                    goToLogin = { navController.navigate(Route.Login) }
                )
            }
        }
    }
}

@Composable
fun BottomBar(navController: NavController) {
    val bottomBarRoutes = listOf(
        BottomBarRoute(
            "Home",
            Route.Home,
            Icons.Default.Home
        ),
        BottomBarRoute(
            "Dashboard",
            Route.Dashboard,
            Icons.Default.AccountBox
        ),
        BottomBarRoute(
            "Notifications",
            Route.Notifications,
            Icons.Default.Notifications
        )

    )
    var currentScreen by remember { mutableStateOf(bottomBarRoutes.first()) }

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination?.route

    if (bottomBarRoutes.map { it.name }.none {
            currentDestination?.contains(it) == true
        }) {
        return // Do not show if destination is from bottomBarRoutes
    }

    BottomNavigation {
        bottomBarRoutes.forEach { topLevelRoute ->
            BottomNavigationItem(
                icon = {
                    Icon(
                        topLevelRoute.icon,
                        contentDescription = topLevelRoute.name
                    )
                },
                label = { Text(topLevelRoute.name) },
                selected = currentScreen.name == topLevelRoute.name,
                onClick = {
                    currentScreen = topLevelRoute
                    navController.navigate(topLevelRoute.route) {
                        // Pop up to the start destination of the graph to
                        // avoid building up a large stack of destinations
                        // on the back stack as users select items
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        // Avoid multiple copies of the same destination when
                        // reselecting the same item
                        launchSingleTop = true
                        // Restore state when reselecting a previously selected item
                        restoreState = true
                    }
                }
            )
        }
    }
}

@Preview
@Composable
fun ButtonBarPreview() {
    MaterialTheme {
        BottomBar(rememberNavController())
    }
}