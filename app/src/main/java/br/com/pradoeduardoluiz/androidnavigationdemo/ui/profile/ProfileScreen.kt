package br.com.pradoeduardoluiz.androidnavigationdemo.ui.profile

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import br.com.pradoeduardoluiz.androidnavigationdemo.common.navigation.Navigator
import org.koin.compose.viewmodel.koinViewModel
import org.koin.compose.koinInject

@Composable
fun ProfileScreen() {
    val viewModel: ProfileViewModel = koinViewModel()
    val navigator: Navigator = koinInject()
    val context = LocalContext.current

    val detailsResult = navigator.results["DETAILS_RESULT"]
    LaunchedEffect(detailsResult) {
        detailsResult?.let {
            Toast.makeText(context, "show result: $it", Toast.LENGTH_SHORT).show()
            navigator.results.remove("DETAILS_RESULT")
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("This is profile Fragment")
        Spacer(Modifier.height(8.dp))
        Button(onClick = { navigator.pop() }) {
            Text("Back")
        }
        Spacer(Modifier.height(8.dp))
        Button(onClick = { viewModel.openDetails() }) {
            Text("Open Details")
        }
    }
}
