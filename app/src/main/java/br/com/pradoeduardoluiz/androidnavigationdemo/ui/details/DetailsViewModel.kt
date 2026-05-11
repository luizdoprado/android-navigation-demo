package br.com.pradoeduardoluiz.androidnavigationdemo.ui.details

import androidx.lifecycle.ViewModel
import br.com.pradoeduardoluiz.androidnavigationdemo.common.navigation.AppRoute
import br.com.pradoeduardoluiz.androidnavigationdemo.common.navigation.Navigator

class DetailsViewModel(
    private val navigator: Navigator
) : ViewModel() {

    fun pop() = navigator.pop()

    fun backWithArguments() {
        navigator.results["DETAILS_RESULT"] = "Result"
        navigator.pop()
    }

    fun login() = navigator.navigate(AppRoute.Login)
}

