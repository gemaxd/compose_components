package com.example.components.dynamic_components.components.singleline

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign

@ExperimentalAnimationApi
@Composable
fun CXCTextField(
    label: String,
    maxLength: Int
) {
    var text by remember { mutableStateOf("") }

    val errorMessage = if (text.length > maxLength) "Limite de caracteres excedido" else ""

    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = text,
            onValueChange = { text = it },
            singleLine = true,
            label = { Text(text = label) }
        )

        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = errorMessage,
                color = if (text.length > 100) Color.Red else Color.Black,
                textAlign = TextAlign.Start
            )
        }
    }
}
