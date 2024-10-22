package br.com.pradoeduardoluiz.androidnavigationdemo.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import br.com.pradoeduardoluiz.androidnavigationdemo.common.ui.CustomButton
import br.com.pradoeduardoluiz.androidnavigationdemo.common.ui.FullScreenText
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = koinViewModel(), // or hiltViewModel() if you use Hilt
    goToProfile: () -> Unit
) {
    val title by viewModel.textFlow.collectAsStateWithLifecycle()
    HomeScreenContent(
        title = title,
        goToProfile = goToProfile
    )
}

@Composable
private fun HomeScreenContent(title: String, goToProfile: () -> Unit) {
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
                text = "Go to Profile",
                onClick = goToProfile
            )
        }
    }
}

@Preview
@Composable
fun HomePreview() {
    MaterialTheme {
        FullScreenText(text = "text")
    }
}

