package br.com.pradoeduardoluiz.androidnavigationdemo.common.navigation

sealed class NavigationState {
    data object Pop: NavigationState()
    data object Idle: NavigationState()
    data object BackWithArguments: NavigationState()
    data class Login(val internalDeeplink: InternalDeeplink? = null): NavigationState()
    data object OpenProfile: NavigationState()
    data object OpenDetails: NavigationState()
}