package br.com.pradoeduardoluiz.androidnavigationdemo.common.navigation

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.snapshots.SnapshotStateMap

class NavigatorImpl : Navigator {

    private val topLevelRoutes: Set<AppRoute> =
        setOf(AppRoute.Home, AppRoute.Dashboard, AppRoute.Notifications)

    private val _backStack = mutableStateListOf<Any>(AppRoute.Home)
    override val backStack: SnapshotStateList<Any> = _backStack

    override val results: SnapshotStateMap<String, Any?> = mutableStateMapOf()

    override val currentTopLevelRoute: AppRoute
        get() = _backStack
            .filterIsInstance<AppRoute>()
            .lastOrNull { it in topLevelRoutes }
            ?: AppRoute.Home

    override fun navigate(route: AppRoute) {
        _backStack.add(route)
    }

    /**
     * Switches to a bottom-nav tab. If the route is already in the stack, pops to it
     * (restoring state). Otherwise clears the current tab route and pushes the new one.
     */
    override fun navigateToTab(route: AppRoute) {
        val idx = _backStack.indexOfLast { it == route }
        if (idx >= 0) {
            while (_backStack.size > idx + 1) _backStack.removeLast()
        } else {
            while (_backStack.size > 1 && _backStack.last() !in topLevelRoutes) {
                _backStack.removeLast()
            }
            if (_backStack.last() != route) {
                if (_backStack.last() in topLevelRoutes) _backStack.removeLast()
                _backStack.add(route)
            }
        }
    }

    override fun pop(): Boolean {
        return if (_backStack.size > 1) {
            _backStack.removeLast()
            true
        } else false
    }

    override fun popTo(route: AppRoute, inclusive: Boolean) {
        val idx = _backStack.indexOfLast { it == route }
        if (idx >= 0) {
            val removeFrom = if (inclusive) idx else idx + 1
            while (_backStack.size > removeFrom) _backStack.removeLast()
        }
    }
}
