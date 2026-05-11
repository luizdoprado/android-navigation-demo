package br.com.pradoeduardoluiz.androidnavigationdemo

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Dashboard
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.ui.NavDisplay
import br.com.pradoeduardoluiz.androidnavigationdemo.common.navigation.AppRoute
import br.com.pradoeduardoluiz.androidnavigationdemo.common.navigation.InternalDeeplink
import br.com.pradoeduardoluiz.androidnavigationdemo.common.navigation.Navigator
import br.com.pradoeduardoluiz.androidnavigationdemo.login.ui.login.LoginScreen
import br.com.pradoeduardoluiz.androidnavigationdemo.ui.deeplink.DeeplinkSimulatorScreen
import br.com.pradoeduardoluiz.androidnavigationdemo.ui.dashboard.DashboardScreen
import br.com.pradoeduardoluiz.androidnavigationdemo.ui.details.DetailsScreen
import br.com.pradoeduardoluiz.androidnavigationdemo.ui.home.HomeScreen
import br.com.pradoeduardoluiz.androidnavigationdemo.ui.notifications.NotificationsScreen
import br.com.pradoeduardoluiz.androidnavigationdemo.ui.profile.ProfileScreen
import br.com.pradoeduardoluiz.androidnavigationdemo.ui.theme.AppTheme
import org.koin.android.ext.android.inject

class MainActivity : ComponentActivity() {

    private val navigator: Navigator by inject()
    private val internalDeeplink: InternalDeeplink by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        handleDeepLink(intent)
        setContent {
            AppTheme {
                val backStack = navigator.backStack
                val topLevelRoutes = setOf(
                    AppRoute.Home, AppRoute.Dashboard, AppRoute.Notifications
                )
                val showBottomBar = backStack.lastOrNull() in topLevelRoutes

                Scaffold(
                    bottomBar = {
                        if (showBottomBar) {
                            NavigationBar {
                                NavigationBarItem(
                                    selected = navigator.currentTopLevelRoute == AppRoute.Home,
                                    onClick = { navigator.navigateToTab(AppRoute.Home) },
                                    icon = { Icon(Icons.Default.Home, contentDescription = "Home") },
                                    label = { Text("Home") }
                                )
                                NavigationBarItem(
                                    selected = navigator.currentTopLevelRoute == AppRoute.Dashboard,
                                    onClick = { navigator.navigateToTab(AppRoute.Dashboard) },
                                    icon = { Icon(Icons.Default.Dashboard, contentDescription = "Dashboard") },
                                    label = { Text("Dashboard") }
                                )
                                NavigationBarItem(
                                    selected = navigator.currentTopLevelRoute == AppRoute.Notifications,
                                    onClick = { navigator.navigateToTab(AppRoute.Notifications) },
                                    icon = { Icon(Icons.Default.Notifications, contentDescription = "Notifications") },
                                    label = { Text("Notifications") }
                                )
                            }
                        }
                    }
                ) { paddingValues ->
                    NavDisplay(
                        backStack = backStack,
                        onBack = { navigator.pop() },
                        modifier = Modifier.padding(paddingValues),
                        entryProvider = entryProvider {
                            entry<AppRoute.Home> { HomeScreen() }
                            entry<AppRoute.Dashboard> { DashboardScreen() }
                            entry<AppRoute.Notifications> { NotificationsScreen() }
                            entry<AppRoute.Profile> { ProfileScreen() }
                            entry<AppRoute.Details> { route -> DetailsScreen(details = route.details) }
                            entry<AppRoute.Login> { LoginScreen() }
                            entry<AppRoute.DeeplinkSimulator> { DeeplinkSimulatorScreen() }
                        }
                    )
                }
            }
        }
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        handleDeepLink(intent)
    }

    private fun handleDeepLink(intent: Intent) {
        intent.data?.toString()?.let { uri ->
            internalDeeplink.routeForUri(uri)?.let { route ->
                navigator.navigate(route)
            }
        }
    }
}
