package com.example.registration

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.pradoeduardoluiz.androidnavigationdemo.common.navigation.NavigatorRouter

class RegistrationViewModel(
    private val navigator: NavigatorRouter
) : ViewModel() {
    private val _text = MutableLiveData<String>().apply {
        value = "This is registration Fragment"
    }
    val text: LiveData<String> = _text

    fun pop() {
        navigator.pop()
    }

}
