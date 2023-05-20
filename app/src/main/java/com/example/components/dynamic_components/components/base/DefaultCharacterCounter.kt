package com.example.components.dynamic_components.components.structurebasis

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign

@Composable
fun DefaultCharacterCounter(
    maxLength: Int = 100,
    errorMessage: String,
    currentText: String
){
    Row(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = errorMessage,
            color = if (currentText.length > maxLength) Color.Red else Color.Black,
            textAlign = TextAlign.Start,
            modifier = Modifier
                .weight(0.7f, true)
        )
        Text(
            text = "${currentText.length}/${maxLength}",
            color = if (currentText.length > maxLength) Color.Red else Color.Black,
            textAlign = TextAlign.End,
            modifier = Modifier
                .weight(0.3f, true)
        )
    }
}