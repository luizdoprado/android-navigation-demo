package br.com.pradoeduardoluiz.androidnavigationdemo.ui.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.pradoeduardoluiz.androidnavigationdemo.common.navigation.route.AppRoute
import br.com.pradoeduardoluiz.androidnavigationdemo.common.navigation.NavigatorRouter
import br.com.pradoeduardoluiz.androidnavigationdemo.common.navigation.route.AppComposeRoute

class ProfileViewModel(
    private val navigator: NavigatorRouter
) : ViewModel() {
    private val _text = MutableLiveData<String>().apply {
        value = "This is profile Fragment"
    }
    val text: LiveData<String> = _text

    fun openDetails() {
        navigator.navigate(AppRoute.Details("Details from Profile Fragment"))
    }

    fun openCompose() {
        navigator.navigate(AppComposeRoute.FirstCompose("FirstCompose from Profile Fragment"))
    }

    fun openSecondCompose() {
        navigator.navigate(AppComposeRoute.SecondCompose)
    }
}
