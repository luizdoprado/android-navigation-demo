package br.com.pradoeduardoluiz.androidnavigationdemo.login.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.pradoeduardoluiz.androidnavigationdemo.common.navigation.NavigatorRouter

class LoginViewModel(
    private val navigator: NavigatorRouter
) : ViewModel() {
    private val _text = MutableLiveData<String>().apply {
        value = "This is login Fragment"
    }
    val text: LiveData<String> = _text

    fun pop() {
        navigator.pop()
    }

}
