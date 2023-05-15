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
import com.example.components.dynamic_components.components.base.BaseDynamicComponent
import com.example.components.feature.dynamic_form.domain.model.Component

@ExperimentalAnimationApi
class TextFieldComponent(
    private val component: Component,
    private val onChange: (Boolean) -> Unit = {},
    private val onTextChange: (String) -> Unit = {}
) : BaseDynamicComponent {
    var text by mutableStateOf("")

    override fun getValue(): String = text

    override fun isValid(): Boolean {
        return text.length <= component.componentMaxLength && text.isNotEmpty()
    }

    @Composable
    override fun GetContent() {
        return CXCTextField(
            text = text,
            component = component,
            onChange = {
                text = it
                onChange(isValid())
                onTextChange(it)
            }
        )
    }
}

@ExperimentalAnimationApi
@Composable
fun CXCTextField(
    text: String,
    component: Component,
    onChange: (String) -> Unit
) {
    var currentText by remember { mutableStateOf(text) }

    val errorMessage = ""

    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = currentText,
            onValueChange = {
                currentText = it
                onChange(it)
            },
            singleLine = true,
            label = { Text(text = component.componentLabel) }
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
