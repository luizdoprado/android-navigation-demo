package br.com.pradoeduardoluiz.androidnavigationdemo.ui.notifications

import androidx.lifecycle.ViewModel
import br.com.pradoeduardoluiz.androidnavigationdemo.common.navigation.AppRoute
import br.com.pradoeduardoluiz.androidnavigationdemo.common.navigation.NavigatorRouter

class NotificationsViewModel(
    private val navigator: NavigatorRouter
) : ViewModel() {
    fun login() = navigator.navigate(AppRoute.Login)
}

