package com.example.compose

import androidx.navigation.NavGraphBuilder
import androidx.navigation.fragment.fragment
import br.com.pradoeduardoluiz.androidnavigationdemo.common.navigation.NavigationNode
import br.com.pradoeduardoluiz.androidnavigationdemo.common.navigation.route.AppRoute

class ComposeNavigationNode : NavigationNode {

    override fun addNode(navGraphBuilder: NavGraphBuilder) {
        navGraphBuilder.apply {
            fragment<ComposeHostFragment, AppRoute.ComposeHost> {}
        }
    }
}