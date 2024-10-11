package br.com.pradoeduardoluiz.androidnavigationdemo.common.navigation

import android.net.Uri
import androidx.core.net.toUri

interface InternalDeeplink {
    fun toLogin(): Uri
}

class InternalDeeplinkImpl : InternalDeeplink {
    override fun toLogin() = "android-app://example.app/login".toUri()
}