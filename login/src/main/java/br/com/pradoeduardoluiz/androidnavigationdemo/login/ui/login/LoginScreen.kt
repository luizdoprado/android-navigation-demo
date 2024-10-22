package br.com.pradoeduardoluiz.androidnavigationdemo.login.ui.login

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import br.com.pradoeduardoluiz.androidnavigationdemo.common.ui.FullScreenText
import org.koin.androidx.compose.koinViewModel

@Composable
fun LoginScreen(
    viewModel: LoginViewModel = koinViewModel()
) {

    val title by viewModel.textFlow.collectAsStateWithLifecycle()
    FullScreenText(title)
}