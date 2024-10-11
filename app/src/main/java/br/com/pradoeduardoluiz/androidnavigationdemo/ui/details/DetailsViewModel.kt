package br.com.pradoeduardoluiz.androidnavigationdemo.ui.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.pradoeduardoluiz.androidnavigationdemo.common.navigation.NavigatorRouter

class DetailsViewModel(
    private val navigator: NavigatorRouter
) : ViewModel() {
    private val _text = MutableLiveData<String>().apply {
        value = "This is details Fragment"
    }
    val text: LiveData<String> = _text

    fun pop() {
        navigator.pop()
    }

    fun backWithArguments() {
        navigator.sendArgumentToBackStackEntry("DETAILS_RESULT" to "Result")
        pop()
    }

    fun login() {
        navigator.navigate(DetailsFragmentDirections.toLogin())
    }
}
