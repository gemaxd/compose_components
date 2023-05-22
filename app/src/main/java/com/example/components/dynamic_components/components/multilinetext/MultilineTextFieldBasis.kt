package com.example.components.dynamic_components.components.multilinetext

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.components.dynamic_components.components.DynamicComponentEvent
import com.example.components.dynamic_components.components.base.BaseDynamicComponent
import com.example.components.dynamic_components.components.base.DefaultComponentHeader
import com.example.components.dynamic_components.components.base.DynamicComponentContainer
import com.example.components.dynamic_components.components.structurebasis.DefaultCharacterCounter
import com.example.components.dynamic_components.components.utils.DEFAULT_MULTILINE_HEIGHT
import com.example.components.dynamic_components.components.utils.enums.keyboard
import com.example.components.feature.dynamic_form.domain.model.Component

@ExperimentalAnimationApi
class MultilineTextFieldBasis(
    private var startText: String = "",
    private val component: Component,
    private val onComponentEvent: (DynamicComponentEvent) -> Unit,
) : BaseDynamicComponent {
    override fun getValue(): String = startText

    override fun isValid(): Boolean {
        return startText.length <= component.componentMaxLength && startText.isNotEmpty()
    }

    @Composable
    override fun Content() {
         return MultilineTextFieldComponent(
            text = startText,
            component = component,
            onChange = {
                startText = it
                onComponentEvent(
                    DynamicComponentEvent.OnTextChange(
                        component, it, isValid()
                    )
                )
            }
        )
    }
}

@Composable
fun MultilineTextFieldComponent(
    text: String,
    component: Component,
    onChange: (String) -> Unit
) {
    var currentText by remember { mutableStateOf(text) }
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
