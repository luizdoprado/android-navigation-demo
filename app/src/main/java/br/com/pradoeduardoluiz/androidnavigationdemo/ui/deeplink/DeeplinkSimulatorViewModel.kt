package br.com.pradoeduardoluiz.androidnavigationdemo.ui.deeplink

import androidx.lifecycle.ViewModel
import br.com.pradoeduardoluiz.androidnavigationdemo.common.navigation.DeeplinkHandler
import br.com.pradoeduardoluiz.androidnavigationdemo.common.navigation.DeeplinkResolver
import br.com.pradoeduardoluiz.androidnavigationdemo.common.navigation.NavigatorRouter

class DeeplinkSimulatorViewModel(
    private val navigator: NavigatorRouter,
    private val deeplinkResolver: DeeplinkResolver,
    private val deeplinkHandler: DeeplinkHandler
) : ViewModel() {

    /**
     * Simulates receiving an external URL (e.g. from a push notification or a web redirect),
     * resolving it to an internal destination, and navigating there.
     *
     * @return true if the URL was resolved and navigation was triggered, false otherwise.
     */
    fun resolveAndNavigate(url: String): Boolean {
        val destination = deeplinkResolver.resolve(url) ?: return false
        deeplinkHandler.handle(destination)
        return true
    }

    fun pop() = navigator.pop()
}
