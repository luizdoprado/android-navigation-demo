package com.example.registration

import androidx.navigation.NavGraphBuilder
import androidx.navigation.fragment.fragment
import br.com.pradoeduardoluiz.androidnavigationdemo.common.navigation.NavigationNode
import br.com.pradoeduardoluiz.androidnavigationdemo.common.navigation.route.RegistrationRoute

class RegistrationNavigationNode : NavigationNode {

    override fun addNode(navGraphBuilder: NavGraphBuilder) {
        navGraphBuilder.apply {
            fragment<RegistrationFragment, RegistrationRoute.Registration> {}
        }
    }
}