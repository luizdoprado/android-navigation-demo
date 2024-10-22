package br.com.pradoeduardoluiz.androidnavigationdemo.ui.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.navigation.toRoute
import br.com.pradoeduardoluiz.androidnavigationdemo.common.navigation.NavigatorRouter
import br.com.pradoeduardoluiz.androidnavigationdemo.ui.Route
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class ProfileViewModel(
    private val navigator: NavigatorRouter,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {
    private val _textFlow = MutableStateFlow("")
    val textFlow = _textFlow.asStateFlow()
    val userId = MutableStateFlow("")

    init {
        if (savedStateHandle.contains("userId")) { // workaround to not break Fragment Navigation
            val profile: Route.Profile = savedStateHandle.toRoute<Route.Profile>()
            userId.value = profile.userId
            _textFlow.value = "This is profile Screen for ${userId.value}"
        }
    }


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
