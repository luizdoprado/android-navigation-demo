package br.com.pradoeduardoluiz.androidnavigationdemo.di

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.fragment.compose.AndroidFragment
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import br.com.pradoeduardoluiz.androidnavigationdemo.DashboardFragmentCompose
import br.com.pradoeduardoluiz.androidnavigationdemo.HomeFragmentCompose
import br.com.pradoeduardoluiz.androidnavigationdemo.NotificationsFragmentCompose
import br.com.pradoeduardoluiz.androidnavigationdemo.common.navigation.NavigationNode
import br.com.pradoeduardoluiz.androidnavigationdemo.common.navigation.route.AppComposeRoute
import br.com.pradoeduardoluiz.androidnavigationdemo.common.navigation.route.AppRoute
import br.com.pradoeduardoluiz.androidnavigationdemo.ui.details.DetailsFragment
import br.com.pradoeduardoluiz.androidnavigationdemo.ui.profile.ProfileFragment
import com.example.compose.ComposeScreen
import com.example.compose.SecondComposeScreen

class ComposeNavigationNode : NavigationNode {

    override fun addNode(navGraphBuilder: NavGraphBuilder) {
        navGraphBuilder.apply {
            composable<AppRoute.Home> { HomeFragmentCompose() }
            composable<AppRoute.Dashboard> { DashboardFragmentCompose() }
            composable<AppRoute.Notifications> { NotificationsFragmentCompose() }
            composable<AppRoute.Profile> { AndroidFragment<ProfileFragment>(modifier = Modifier.fillMaxSize()) }
            composable<AppRoute.Details> { AndroidFragment<DetailsFragment>(modifier = Modifier.fillMaxSize()) }
            composable<AppComposeRoute.FirstCompose> { backStackEntry ->
                val firstCompose: AppComposeRoute.FirstCompose = backStackEntry.toRoute()
                ComposeScreen(firstCompose.details) {}
            }
            composable<AppComposeRoute.SecondCompose> { SecondComposeScreen() }
        }
    }
}