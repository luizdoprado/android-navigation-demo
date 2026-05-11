package br.com.pradoeduardoluiz.androidnavigationdemo.common.navigation

import android.net.Uri
import androidx.core.net.toUri

interface InternalDeeplink {
    fun toLogin(): Uri
    fun toHome(): Uri
    fun toDashboard(): Uri
    fun toNotifications(): Uri
    fun toProfile(): Uri
    fun toDetails(details: String): Uri
}

class InternalDeeplinkImpl : InternalDeeplink {
    override fun toLogin() = "android-app://example.app/login".toUri()
    override fun toHome() = "android-app://example.app/home".toUri()
    override fun toDashboard() = "android-app://example.app/dashboard".toUri()
    override fun toNotifications() = "android-app://example.app/notifications".toUri()
    override fun toProfile() = "android-app://example.app/profile".toUri()
    override fun toDetails(details: String) = "android-app://example.app/details/$details".toUri()
}