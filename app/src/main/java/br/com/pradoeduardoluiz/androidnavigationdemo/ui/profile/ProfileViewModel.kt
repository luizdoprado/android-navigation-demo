package br.com.pradoeduardoluiz.androidnavigationdemo.ui.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.pradoeduardoluiz.androidnavigationdemo.common.navigation.NavigatorRouter

class ProfileViewModel(
    private val navigator: NavigatorRouter
) : ViewModel() {
    private val _text = MutableLiveData<String>().apply {
        value = "This is profile Fragment"
    }
    val text: LiveData<String> = _text

    fun openDetails() {
        val details = "Open From Profile"
        val direction = ProfileFragmentDirections.toDetails(details)
        navigator.navigate(direction)
    }
}
