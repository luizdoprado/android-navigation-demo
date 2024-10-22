package br.com.pradoeduardoluiz.androidnavigationdemo.ui.notifications

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.pradoeduardoluiz.androidnavigationdemo.common.navigation.InternalDeeplink
import br.com.pradoeduardoluiz.androidnavigationdemo.common.navigation.Navigator
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class NotificationsViewModel(
    private val navigator: Navigator,
    private val internalDeeplink: InternalDeeplink
) : ViewModel() {
    private val _textFlow = MutableStateFlow("This is notifications Compose screen")
    val textFlow = _textFlow.asStateFlow()

    private val _text = MutableLiveData<String>().apply {
        value = "This is notifications Fragment"
    }
    val text: LiveData<String> = _text

    fun login() {
        navigator.navigate(internalDeeplink.toLogin())
    }
}
