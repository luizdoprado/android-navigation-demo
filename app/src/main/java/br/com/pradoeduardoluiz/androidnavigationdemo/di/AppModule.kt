package br.com.pradoeduardoluiz.androidnavigationdemo.di

import br.com.pradoeduardoluiz.androidnavigationdemo.common.navigation.InternalDeeplink
import br.com.pradoeduardoluiz.androidnavigationdemo.common.navigation.InternalDeeplinkImpl
import br.com.pradoeduardoluiz.androidnavigationdemo.common.navigation.NavigationNode
import br.com.pradoeduardoluiz.androidnavigationdemo.common.navigation.Navigator
import br.com.pradoeduardoluiz.androidnavigationdemo.common.navigation.NavigatorImpl
import br.com.pradoeduardoluiz.androidnavigationdemo.login.ui.login.LoginNavigationNode
import br.com.pradoeduardoluiz.androidnavigationdemo.login.ui.login.LoginViewModel
import br.com.pradoeduardoluiz.androidnavigationdemo.ui.details.DetailsViewModel
import br.com.pradoeduardoluiz.androidnavigationdemo.ui.notifications.NotificationsViewModel
import br.com.pradoeduardoluiz.androidnavigationdemo.ui.profile.ProfileViewModel
import com.example.compose.ComposeNavigationNode
import com.example.registration.RegistrationNavigationNode
import com.example.registration.RegistrationViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val appModule = module {
    single<Navigator> { NavigatorImpl() }
    single<InternalDeeplink> { InternalDeeplinkImpl() }
//    viewModel { HomeViewModel() }
//    viewModel { DashboardViewModel() }
    viewModel { NotificationsViewModel(navigator = get<Navigator>(), internalDeeplink = get()) }
    viewModel { ProfileViewModel(navigator = get<Navigator>()) }
    viewModel { DetailsViewModel(navigator = get<Navigator>()) }

    single<NavigationNode>(named("A")) { LoginNavigationNode() }
    single<NavigationNode>(named("B")) { RegistrationNavigationNode() }
    single<NavigationNode>(named("C")) { ComposeNavigationNode() }

    single<Set<NavigationNode>> {
        setOf(
            get<NavigationNode>(named("A")),
            get<NavigationNode>(named("B")),
            get<NavigationNode>(named("C"))
        )
    }
}

val loginModule = module {
    viewModel { LoginViewModel(navigator = get<Navigator>()) }
}

val registrationModule = module {
    viewModel { RegistrationViewModel(navigator = get<Navigator>()) }
}