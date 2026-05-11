package br.com.pradoeduardoluiz.androidnavigationdemo.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun HomeScreen() {
    val viewModel: HomeViewModel = koinViewModel()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("This is home Fragment")
        Spacer(Modifier.height(16.dp))
        Button(onClick = { viewModel.openProfile() }) {
            Text("Open Profile")
        }
        Spacer(Modifier.height(8.dp))
        Button(onClick = { viewModel.openDeeplinkSimulator() }) {
            Text("Deeplink Simulator")
        }
    }
}
