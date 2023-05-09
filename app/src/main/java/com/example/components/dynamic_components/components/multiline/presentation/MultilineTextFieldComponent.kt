package com.example.components.dynamic_components.components.multiline.presentation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.unit.dp
import com.example.components.dynamic_components.components.base.BaseDynamicComponent
import com.example.components.dynamic_components.components.utils.DEFAULT_MULTILINE_HEIGHT
import com.example.components.feature.dynamic_form.domain.model.Component

@ExperimentalAnimationApi
class MultilineTextFieldComponent(
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
    override fun getContent() {
         return CXCMultilineTextField(
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

@Composable
fun CXCMultilineTextField(
    text: String,
    component: Component,
    onChange: (String) -> Unit
) {
    var currentText by remember { mutableStateOf(text) }
    val errorMessage = if (text.length > component.componentMaxLength) "Limite de caracteres excedido" else ""

    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
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
            label = { Text(text = component.componentLabel) }
        )

        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = errorMessage,
                color = if (currentText.length > component.componentMaxLength) Color.Red else Color.Black,
                textAlign = TextAlign.Start,
                modifier = Modifier
                    .weight(0.7f, true)
            )
            Text(
                text = "${currentText.length}/${component.componentMaxLength}",
                color = if (currentText.length > component.componentMaxLength) Color.Red else Color.Black,
                textAlign = TextAlign.End,
                modifier = Modifier
                    .weight(0.3f, true)
            )
        }
    }
}
