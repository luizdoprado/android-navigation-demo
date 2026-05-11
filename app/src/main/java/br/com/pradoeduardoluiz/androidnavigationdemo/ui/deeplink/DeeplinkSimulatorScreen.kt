package br.com.pradoeduardoluiz.androidnavigationdemo.ui.deeplink

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.koin.compose.viewmodel.koinViewModel

private val sampleUrls = listOf(
    "https://example.com/home",
    "https://example.com/dashboard",
    "https://example.com/notifications",
    "https://example.com/profile",
    "https://example.com/details/item-123",
    "https://example.com/login"
)

@Composable
fun DeeplinkSimulatorScreen(
    viewModel: DeeplinkSimulatorViewModel = koinViewModel()
) {
    var url by remember { mutableStateOf("") }
    var message by remember { mutableStateOf<String?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text("Deeplink Simulator", style = MaterialTheme.typography.headlineMedium)

        Text(
            text = "Simulate an external URL (e.g. from a push notification) being resolved to an internal screen.",
            style = MaterialTheme.typography.bodyMedium
        )

        OutlinedTextField(
            value = url,
            onValueChange = { url = it; message = null },
            label = { Text("URL") },
            placeholder = { Text("https://example.com/home") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        Button(
            onClick = {
                val success = viewModel.resolveAndNavigate(url)
                message = if (success) "✓ Navigating..." else "✗ URL could not be resolved."
            },
            modifier = Modifier.fillMaxWidth(),
            enabled = url.isNotBlank()
        ) {
            Text("Resolve & Navigate")
        }

        message?.let {
            val isSuccess = it.startsWith("✓")
            Text(
                text = it,
                color = if (isSuccess) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall
            )
        }

        Spacer(Modifier.height(4.dp))

        Text("Sample URLs:", style = MaterialTheme.typography.labelLarge)

        sampleUrls.forEach { sample ->
            TextButton(
                onClick = { url = sample; message = null },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(sample, style = MaterialTheme.typography.bodySmall)
            }
        }

        Spacer(Modifier.height(8.dp))

        OutlinedButton(
            onClick = { viewModel.pop() },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Back")
        }
    }
}
