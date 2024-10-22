package br.com.pradoeduardoluiz.androidnavigationdemo.ui.dashboard

import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import br.com.pradoeduardoluiz.androidnavigationdemo.common.ui.FullScreenText
import org.koin.androidx.compose.koinViewModel

@Composable
fun DashboardScreen(
    viewModel: DashboardViewModel = koinViewModel()
) {
    val title by viewModel.textFlow.collectAsStateWithLifecycle()
    FullScreenText(title)
}
