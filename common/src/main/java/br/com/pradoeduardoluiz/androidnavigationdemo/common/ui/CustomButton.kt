package br.com.pradoeduardoluiz.androidnavigationdemo.common.ui

import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp

@Composable
fun CustomButton(
    text: String,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        shape = MaterialTheme.shapes.medium
    ) {
        Text(
            text = text,
            style = TextStyle.Default.copy(fontSize = 20.sp)
        )
    }
}