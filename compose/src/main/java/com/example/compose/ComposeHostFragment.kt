package com.example.compose

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.toRoute
import br.com.pradoeduardoluiz.androidnavigationdemo.common.navigation.route.AppComposeRoute
import br.com.pradoeduardoluiz.androidnavigationdemo.common.navigation.route.AppRoute

class ComposeHostFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return ComposeView(requireContext()).apply {
            val route = findNavController()
                .getBackStackEntry<AppRoute.ComposeHost>()
                .toRoute<AppRoute.ComposeHost>()

//            val starDestination = when (route.composeRoute) {
//                AppComposeRoute.FirstCompose.route -> AppComposeRoute.FirstCompose
//                AppComposeRoute.SecondCompose.route -> AppComposeRoute.SecondCompose
//                else -> AppComposeRoute.FirstCompose
//            }
//
//            setContent {
//                val navController = rememberNavController()
//                NavHost(navController = navController, startDestination = starDestination) {
//                    composable<AppComposeRoute.FirstCompose> {
//                        ComposeScreen(
//                            onNavigationFragment = {
//                                findNavController().navigate(it)
//                            },
//                            onNavigationCompose = {
//                                navController.navigate(it)
//                            }
//                        )
//                    }
//                    composable<AppComposeRoute.SecondCompose> { SecondComposeScreen() }
//                }
//            }
        }
    }

}