package br.com.pradoeduardoluiz.androidnavigationdemo.common.navigation

import androidx.navigation.NavGraphBuilder

interface NavigationNode {
    fun addNode(navGraphBuilder: NavGraphBuilder)
}