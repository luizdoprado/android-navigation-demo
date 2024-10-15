package br.com.pradoeduardoluiz.androidnavigationdemo.ui.base

import br.com.pradoeduardoluiz.androidnavigationdemo.common.navigation.NavigationState
import br.com.pradoeduardoluiz.androidnavigationdemo.common.navigation.Navigator
import br.com.pradoeduardoluiz.androidnavigationdemo.ui.details.DetailsFragmentDirections
import br.com.pradoeduardoluiz.androidnavigationdemo.ui.home.HomeFragmentDirections
import br.com.pradoeduardoluiz.androidnavigationdemo.ui.profile.ProfileFragmentDirections

class NavigatorRoute(private val navigator: Navigator) {
    fun navigateTo(state: NavigationState) {
        when(state){
            NavigationState.Idle -> {}
            NavigationState.BackWithArguments -> {
                navigator.sendArgumentToBackStackEntry("DETAILS_RESULT" to "Result")
                navigator.pop()
            }
             is NavigationState.Login -> {
                 if(state.internalDeeplink != null) {
                     navigator.navigate(state.internalDeeplink!!.toLogin())
                 } else {
                     navigator.navigate(DetailsFragmentDirections.toLogin())
                 }
            }
            NavigationState.Pop -> {
                navigator.pop()
            }
            NavigationState.OpenProfile -> {
                navigator.navigate(HomeFragmentDirections.toProfile())
            }
            NavigationState.OpenDetails -> {
                val details = "Open From Profile"
                val direction = ProfileFragmentDirections.toDetails(details)
                navigator.navigate(direction)
            }
        }
    }
}