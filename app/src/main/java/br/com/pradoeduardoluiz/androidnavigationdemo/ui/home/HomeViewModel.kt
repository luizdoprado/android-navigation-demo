package br.com.pradoeduardoluiz.androidnavigationdemo.ui.home

import androidx.lifecycle.ViewModel
import br.com.pradoeduardoluiz.androidnavigationdemo.common.navigation.AppRoute
import br.com.pradoeduardoluiz.androidnavigationdemo.common.navigation.NavigatorRouter

class HomeViewModel(private val navigator: NavigatorRouter) : ViewModel() {
    fun openProfile() = navigator.navigate(AppRoute.Profile)
    fun openDeeplinkSimulator() = navigator.navigate(AppRoute.DeeplinkSimulator)
}
