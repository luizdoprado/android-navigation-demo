package br.com.pradoeduardoluiz.androidnavigationdemo.ui.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.navigation.toRoute
import br.com.pradoeduardoluiz.androidnavigationdemo.common.navigation.NavigatorRouter
import br.com.pradoeduardoluiz.androidnavigationdemo.ui.Route
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class DetailsViewModel(
    private val navigator: NavigatorRouter,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _textFlow = MutableStateFlow("")

    init {
        if (savedStateHandle.contains("userId")) { // workaround to not break Fragment Navigation
            val profile = savedStateHandle.toRoute<Route.Details>()
            _textFlow.value = "This is details Compose screen for ${profile.userId}"
        }
    }


    val textFlow = _textFlow.asStateFlow()

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
