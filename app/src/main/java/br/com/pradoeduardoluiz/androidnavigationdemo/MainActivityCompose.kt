package br.com.pradoeduardoluiz.androidnavigationdemo

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material3.MaterialTheme
import br.com.pradoeduardoluiz.androidnavigationdemo.ui.MainScreen
import org.koin.androidx.compose.KoinAndroidContext

class MainActivityCompose : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                KoinAndroidContext {  // Set current Koin instance to Compose context
                    MainScreen() // The main Composable
                }
            }
        }
    }
}