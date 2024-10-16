package br.com.pradoeduardoluiz.androidnavigationdemo.di

import br.com.pradoeduardoluiz.androidnavigationdemo.common.navigation.InternalDeeplink
import br.com.pradoeduardoluiz.androidnavigationdemo.common.navigation.InternalDeeplinkImpl
import br.com.pradoeduardoluiz.androidnavigationdemo.common.navigation.Navigator
import br.com.pradoeduardoluiz.androidnavigationdemo.common.navigation.NavigatorImpl
import br.com.pradoeduardoluiz.androidnavigationdemo.login.ui.login.LoginViewModel
import br.com.pradoeduardoluiz.androidnavigationdemo.ui.details.DetailsViewModel
import br.com.pradoeduardoluiz.androidnavigationdemo.ui.notifications.NotificationsViewModel
import br.com.pradoeduardoluiz.androidnavigationdemo.ui.profile.ProfileViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single<Navigator> { NavigatorImpl() }
    single<InternalDeeplink> { InternalDeeplinkImpl() }
//    viewModel { HomeViewModel() }
//    viewModel { DashboardViewModel() }
    viewModel { NotificationsViewModel(navigator = get<Navigator>(), internalDeeplink = get()) }
    viewModel { ProfileViewModel(navigator = get<Navigator>()) }
    viewModel { DetailsViewModel(navigator = get<Navigator>()) }
}

val loginModule = module {
    viewModel { LoginViewModel(navigator = get<Navigator>()) }
}