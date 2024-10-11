package br.com.pradoeduardoluiz.androidnavigationdemo.ui.notifications

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.pradoeduardoluiz.androidnavigationdemo.common.navigation.InternalDeeplink
import br.com.pradoeduardoluiz.androidnavigationdemo.common.navigation.Navigator

class NotificationsViewModel(
    private val navigator: Navigator,
    private val internalDeeplink: InternalDeeplink
) : ViewModel() {
    private val _text = MutableLiveData<String>().apply {
        value = "This is notifications Fragment"
    }
    val text: LiveData<String> = _text

    fun login() {
        navigator.navigate(internalDeeplink.toLogin())
    }
}
