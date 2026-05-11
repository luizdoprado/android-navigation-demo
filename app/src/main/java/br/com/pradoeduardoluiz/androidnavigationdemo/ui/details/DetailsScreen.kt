package br.com.pradoeduardoluiz.androidnavigationdemo.ui.details

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
fun DetailsScreen(details: String) {
    val viewModel: DetailsViewModel = koinViewModel()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(details)
        Spacer(Modifier.height(8.dp))
        Text("This is details Fragment")
        Spacer(Modifier.height(16.dp))
        Button(onClick = { viewModel.pop() }) {
            Text("Back")
        }
        Spacer(Modifier.height(8.dp))
        Button(onClick = { viewModel.backWithArguments() }) {
            Text("Back with arguments")
        }
        Spacer(Modifier.height(8.dp))
        Button(onClick = { viewModel.login() }) {
            Text("Login")
        }
    }
}
