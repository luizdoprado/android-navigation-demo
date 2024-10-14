package br.com.pradoeduardoluiz.androidnavigationdemo.ui.notifications

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.pradoeduardoluiz.androidnavigationdemo.common.navigation.InternalDeeplink
import br.com.pradoeduardoluiz.androidnavigationdemo.common.navigation.NavigationState
import br.com.pradoeduardoluiz.androidnavigationdemo.common.navigation.Navigator
import br.com.pradoeduardoluiz.androidnavigationdemo.ui.base.BaseViewModel

class NotificationsViewModel(
    private val internalDeeplink: InternalDeeplink
) : BaseViewModel() {
    private val _text = MutableLiveData<String>().apply {
        value = "This is notifications Fragment"
    }
    val text: LiveData<String> = _text

    fun login() {
        _navigationState.value = NavigationState.Login(internalDeeplink)
    }
}
