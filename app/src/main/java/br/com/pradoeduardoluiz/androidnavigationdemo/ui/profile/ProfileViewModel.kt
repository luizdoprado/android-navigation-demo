package br.com.pradoeduardoluiz.androidnavigationdemo.ui.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.com.pradoeduardoluiz.androidnavigationdemo.common.navigation.NavigationState
import br.com.pradoeduardoluiz.androidnavigationdemo.ui.base.BaseViewModel

class ProfileViewModel : BaseViewModel() {
    private val _text = MutableLiveData<String>().apply {
        value = "This is profile Fragment"
    }
    val text: LiveData<String> = _text

    fun openDetails() {
        _navigationState.value = NavigationState.OpenDetails
    }
}
