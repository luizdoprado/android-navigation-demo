package br.com.pradoeduardoluiz.androidnavigationdemo

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import br.com.pradoeduardoluiz.androidnavigationdemo.common.navigation.NavigationNode
import br.com.pradoeduardoluiz.androidnavigationdemo.common.navigation.Navigator
import br.com.pradoeduardoluiz.androidnavigationdemo.common.navigation.route.AppRoute
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    private val navigator: Navigator by inject()
    private val navigationNodes: Set<NavigationNode> by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val navController = rememberNavController()
            navigator.bind(navController, lifecycleScope = lifecycleScope)
            Scaffold(
                bottomBar = {
                    NavigationBar {
                        NavigationBarItem(
                            icon = {
                                Icon(
                                    painter = painterResource(R.drawable.ic_home_black_24dp),
                                    contentDescription = null
                                )
                            },
                            label = { Text("Home") },
                            selected = false,
                            onClick = {
                                navController.navigate(AppRoute.Home)
                            }
                        )
                        NavigationBarItem(
                            icon = {
                                Icon(
                                    painter = painterResource(R.drawable.ic_dashboard_black_24dp),
                                    contentDescription = null
                                )
                            },
                            label = { Text("Dashboard") },
                            selected = false,
                            onClick = {
                                navController.navigate(AppRoute.Dashboard)
                            }
                        )
                        NavigationBarItem(
                            icon = {
                                Icon(
                                    painter = painterResource(R.drawable.ic_home_black_24dp),
                                    contentDescription = null
                                )
                            },
                            label = { Text("Notifications") },
                            selected = false,
                            onClick = {
                                navController.navigate(AppRoute.Notifications)
                            }
                        )
                    }
                }
            ) { innerPadding ->
                NavHost(
                    modifier = Modifier.padding(innerPadding),
                    navController = navController,
                    startDestination = AppRoute.Home
                ) {
                    navigationNodes.forEach { navNode ->
                        navNode.addNode(this)
                    }
                }
            }
        }
    }
}
