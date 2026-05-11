package br.com.pradoeduardoluiz.androidnavigationdemo.ui.profile

import androidx.lifecycle.ViewModel
import br.com.pradoeduardoluiz.androidnavigationdemo.common.navigation.AppRoute
import br.com.pradoeduardoluiz.androidnavigationdemo.common.navigation.NavigatorRouter

class ProfileViewModel(
    private val navigator: NavigatorRouter
) : ViewModel() {
    fun openDetails() = navigator.navigate(AppRoute.Details("Open From Profile"))
}

