package br.com.pradoeduardoluiz.androidnavigationdemo.login.ui.login

import androidx.lifecycle.ViewModel
import br.com.pradoeduardoluiz.androidnavigationdemo.common.navigation.NavigatorRouter

class LoginViewModel(
    private val navigator: NavigatorRouter
) : ViewModel() {
    fun pop() = navigator.pop()
}

