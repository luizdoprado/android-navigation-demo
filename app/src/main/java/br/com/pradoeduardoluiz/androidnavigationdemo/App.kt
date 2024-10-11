package br.com.pradoeduardoluiz.androidnavigationdemo

import android.app.Application
import br.com.pradoeduardoluiz.androidnavigationdemo.di.appModule
import br.com.pradoeduardoluiz.androidnavigationdemo.di.loginModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(appModule + loginModule)
        }
    }
}