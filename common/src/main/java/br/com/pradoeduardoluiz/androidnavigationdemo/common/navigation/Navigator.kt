package br.com.pradoeduardoluiz.androidnavigationdemo.common.navigation

import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.snapshots.SnapshotStateMap

interface Navigator : NavigatorRouter {
    val backStack: SnapshotStateList<Any>
    val results: SnapshotStateMap<String, Any?>
    val currentTopLevelRoute: AppRoute
    fun navigateToTab(route: AppRoute)
}

interface NavigatorRouter {
    fun navigate(route: AppRoute)
    fun pop(): Boolean
    fun popTo(route: AppRoute, inclusive: Boolean = false)
}

