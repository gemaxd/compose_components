package com.example.components.dynamic_components.components.singlelinetext

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import com.example.components.dynamic_components.components.base.DefaultComponentHeader
import com.example.components.dynamic_components.components.base.DynamicComponentContainer

@ExperimentalAnimationApi
@Composable
fun TextFieldContent(
    title: String,
    description: String?,
    keyboard: KeyboardType,
    onChange: ((String) -> Unit)? = null,
    onDone: ((String) -> Unit)? = null
) {
    var currentText by remember { mutableStateOf("") }
    val visualTransformation =
        if (keyboard == KeyboardType.Password || keyboard == KeyboardType.NumberPassword){
            PasswordVisualTransformation()
        } else {
            VisualTransformation.None
        }

    DynamicComponentContainer(
        header = {
            DefaultComponentHeader(
                modifier = Modifier.fillMaxWidth(),
                title = title,
                description = description
            )
        },
        content = {
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = currentText,
                onValueChange = {
                    currentText = it
                    onChange?.invoke(it)
                },
                singleLine = true,
                visualTransformation = visualTransformation,
                keyboardOptions = KeyboardOptions(
                    keyboardType = keyboard
                ),
                keyboardActions = KeyboardActions(
                    onDone = {
                        onDone?.let { action ->
                            if(currentText.isNotEmpty()){
                                action.invoke(currentText)
                                currentText = ""
                            }
                        }
                    }
                )
            )
        }
    )
}