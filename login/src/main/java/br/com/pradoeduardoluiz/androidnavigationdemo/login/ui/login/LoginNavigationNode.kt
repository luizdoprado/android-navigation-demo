package br.com.pradoeduardoluiz.androidnavigationdemo.login.ui.login

import androidx.navigation.NavGraphBuilder
import androidx.navigation.fragment.fragment
import br.com.pradoeduardoluiz.androidnavigationdemo.common.navigation.route.LoginRoute
import br.com.pradoeduardoluiz.androidnavigationdemo.common.navigation.NavigationNode

class LoginNavigationNode : NavigationNode {

    override fun addNode(navGraphBuilder: NavGraphBuilder) {
        navGraphBuilder.apply {
//            fragment<LoginFragment, LoginRoute.Login> {}
        }
    }
}