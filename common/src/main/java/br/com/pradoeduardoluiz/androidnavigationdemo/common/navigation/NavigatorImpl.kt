package br.com.pradoeduardoluiz.androidnavigationdemo.common.navigation

import android.net.Uri
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NavigatorImpl : Navigator {

    private var lifecycleScope: LifecycleCoroutineScope? = null
    private var navController: NavController? = null

    override fun bind(navController: NavController, lifecycleScope: LifecycleCoroutineScope) {
        this.lifecycleScope = lifecycleScope
        this.navController = navController
    }

    override fun unbind() {
        this.lifecycleScope = null
        this.navController = null
    }

    override fun navigate(directions: NavDirections) {
        lifecycleScope?.launch(Dispatchers.Main) {
            navController?.navigate(directions)
        }
    }

    override fun navigate(deeplink: Uri) {
        lifecycleScope?.launch(Dispatchers.Main) {
            navController?.navigate(deeplink)
        }
    }

    override fun pop() {
        lifecycleScope?.launch(Dispatchers.Main) {
            navController?.popBackStack()
        }
    }

    override fun popTo(destinationId: Int, inclusive: Boolean) {
        lifecycleScope?.launch(Dispatchers.Main) {
            navController?.popBackStack(destinationId, inclusive)
        }
    }

    override fun <T> sendArgumentToBackStackEntry(argument: Pair<String, T>?) {
        lifecycleScope?.launch(Dispatchers.Main) {
            navController?.previousBackStackEntry?.savedStateHandle?.set(
                argument?.first.orEmpty(),
                argument?.second
            )
        }
    }
}