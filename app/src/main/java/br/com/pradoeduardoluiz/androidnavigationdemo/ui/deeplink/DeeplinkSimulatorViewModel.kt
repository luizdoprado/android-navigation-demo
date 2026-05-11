package br.com.pradoeduardoluiz.androidnavigationdemo.ui.deeplink

import androidx.lifecycle.ViewModel
import br.com.pradoeduardoluiz.androidnavigationdemo.common.navigation.InternalDeeplink
import br.com.pradoeduardoluiz.androidnavigationdemo.common.navigation.DeeplinkResolver
import br.com.pradoeduardoluiz.androidnavigationdemo.common.navigation.NavigatorRouter

class DeeplinkSimulatorViewModel(
    private val navigator: NavigatorRouter,
    private val deeplinkResolver: DeeplinkResolver,
    private val internalDeeplink: InternalDeeplink
) : ViewModel() {

    /**
     * Simulates receiving an external URL (e.g. from a push notification or a web redirect).
     * The resolver maps it to an internal URI, which is then parsed into an [AppRoute]
     * by [InternalDeeplink] using the pattern-based matcher.
     *
     * @return true if the URL was resolved and navigation was triggered, false otherwise.
     */
    fun resolveAndNavigate(url: String): Boolean {
        val internalUri = deeplinkResolver.resolve(url) ?: return false
        val route = internalDeeplink.routeForUri(internalUri) ?: return false
        navigator.navigate(route)
        return true
    }

    fun pop() = navigator.pop()
}
