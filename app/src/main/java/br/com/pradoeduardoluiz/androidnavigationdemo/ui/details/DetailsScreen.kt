package br.com.pradoeduardoluiz.androidnavigationdemo.ui.details

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import br.com.pradoeduardoluiz.androidnavigationdemo.common.ui.CustomButton
import br.com.pradoeduardoluiz.androidnavigationdemo.common.ui.FullScreenText
import org.koin.androidx.compose.koinViewModel

@Composable
fun DetailsScreen(
    viewModel: DetailsViewModel = koinViewModel(),
    goBackWithArguments: () -> Unit,
    goToLogin: () -> Unit
) {
    val title by viewModel.textFlow.collectAsStateWithLifecycle()
    FullScreenText(title)

    Box(Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 30.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Bottom
        ) {
            CustomButton(
                text = "Go Back With Arguments",
                onClick = goBackWithArguments
            )
            Spacer(Modifier.height(12.dp))
            CustomButton(
                text = "Go to Login",
                onClick = goToLogin
            )
        }
    }
}