package com.example.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.navigation.compose.rememberNavController
import androidx.navigation.findNavController
import br.com.pradoeduardoluiz.androidnavigationdemo.common.navigation.route.AppComposeRoute
import br.com.pradoeduardoluiz.androidnavigationdemo.common.navigation.route.LoginRoute
import br.com.pradoeduardoluiz.androidnavigationdemo.common.navigation.route.Route

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ComposeScreen(
    details: String = "",
    onNavigate: (Route) -> Unit = {},
) {
    Scaffold(
        topBar = {
            // This is where you can add a top bar if needed.
            // For now, we will just display a simple text.
            TopAppBar(title = {
                Text(text = "Compose Screen")
            })
        }
    ) { innerPadding ->
        // The content of the screen goes here.
        // You can use the innerPadding to ensure proper spacing.
        Column(
            modifier = Modifier
                .background(Color.Red)
                .padding(innerPadding)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = details,
                modifier = Modifier.padding(innerPadding)
            )
            Button(onClick = {}) {
                Text(text = "Navigate Login Frament")
            }

            Button(onClick = { onNavigate(AppComposeRoute.SecondCompose) }) {
                Text(text = "Navigate to Second Compose Screen")
            }
        }
    }
}

@PreviewLightDark
@Composable
fun ComposeScreenPreview() {
    // This is a preview function for the ComposeScreen.
    // It allows you to see how the UI looks in both light and dark themes.
    ComposeScreen(details = "")
}