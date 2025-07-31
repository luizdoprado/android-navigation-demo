package br.com.pradoeduardoluiz.androidnavigationdemo.common.navigation

import android.net.Uri
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.NavOptions
import androidx.navigation.Navigator

interface Navigator : NavigatorBinder, NavigatorRouter

interface NavigatorBinder {
    fun bind(navController: NavController, lifecycleScope: LifecycleCoroutineScope)
    fun unbind()
}

interface NavigatorRouter {
    fun navigate(directions: NavDirections)
    fun navigate(deeplink: Uri)
    fun navigate(route: String)
    fun <T : Any> navigate(route: T)
    fun pop()
    fun popTo(destinationId: Int, inclusive: Boolean = false)
    fun <T> sendArgumentToBackStackEntry(argument: Pair<String, T>?)
    fun navController(): NavController
}

