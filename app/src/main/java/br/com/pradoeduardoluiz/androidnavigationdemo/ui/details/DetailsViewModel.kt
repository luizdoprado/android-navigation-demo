package br.com.pradoeduardoluiz.androidnavigationdemo.ui.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.com.pradoeduardoluiz.androidnavigationdemo.common.navigation.NavigationState
import br.com.pradoeduardoluiz.androidnavigationdemo.ui.base.BaseViewModel

class DetailsViewModel(
) : BaseViewModel() {
    private val _text = MutableLiveData<String>().apply {
        value = "This is details Fragment"
    }
    val text: LiveData<String> = _text


    fun backWithArguments() {
        _navigationState.value = NavigationState.BackWithArguments
    }

    fun login() {
        _navigationState.value = NavigationState.Login()
    }
}
