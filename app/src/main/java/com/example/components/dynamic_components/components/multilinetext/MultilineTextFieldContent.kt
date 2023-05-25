package com.example.components.dynamic_components.components.multilinetext

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.components.dynamic_components.components.base.DefaultComponentHeader
import com.example.components.dynamic_components.components.base.DynamicComponentContainer
import com.example.components.dynamic_components.components.structurebasis.DefaultCharacterCounter
import com.example.components.dynamic_components.components.utils.DEFAULT_MULTILINE_HEIGHT
import com.example.components.dynamic_components.components.utils.enums.keyboard
import com.example.components.feature.dynamic_form.domain.model.Component

@Composable
fun MultilineTextFieldContent(
    text: String,
    component: Component,
    onChange: (String) -> Unit
) {
    var currentText by rememberSaveable { mutableStateOf(text) }
    val errorMessage = if (currentText.length > component.componentMaxLength) "Limite de caracteres excedido" else ""

    DynamicComponentContainer(
        header = {
            DefaultComponentHeader(
                modifier = Modifier.fillMaxWidth(),
                title = component.componentTitle,
                description = component.componentDescription
            )
        },
        content = {
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(DEFAULT_MULTILINE_HEIGHT.dp),
                value = currentText,
                onValueChange = {
                    currentText = it
                    onChange(it)
                },
                maxLines = 4,
                keyboardOptions = KeyboardOptions(
                    keyboardType = component.componentInputType.keyboard()
                )
            )
        },
        footer = {
            DefaultCharacterCounter(
                maxLength = component.componentMaxLength,
                currentText = currentText,
                errorMessage = errorMessage
            )
        }
    )
}