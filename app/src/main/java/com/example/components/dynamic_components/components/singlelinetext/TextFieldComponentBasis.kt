package com.example.components.dynamic_components.components.singlelinetext

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
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
import com.example.components.dynamic_components.components.DynamicComponentEvent
import com.example.components.dynamic_components.components.base.BaseDynamicComponent
import com.example.components.dynamic_components.components.structurebasis.DefaultComponentHeader
import com.example.components.dynamic_components.components.utils.keyboard
import com.example.components.feature.dynamic_form.domain.model.Component

@ExperimentalAnimationApi
class TextFieldComponentBasis(
    private val component: Component,
    private val onComponentEvent: (DynamicComponentEvent) -> Unit,
) : BaseDynamicComponent {
    private var text by mutableStateOf("")

    override fun getValue(): String = text

    override fun isValid(): Boolean {
        return text.length <= component.componentMaxLength && text.isNotEmpty()
    }

    @Composable
    override fun Content() {
        return TextFieldContent(
            text = text,
            component = component,
            onChange = {
                text = it
                onComponentEvent(
                    DynamicComponentEvent.OnTextChange(
                        component, it, isValid()
                    )
                )
            }
        )
    }
}

@ExperimentalAnimationApi
@Composable
fun TextFieldContent(
    text: String,
    component: Component,
    onChange: (String) -> Unit
) {
    var currentText by remember { mutableStateOf(text) }
    val keyboard = component.componentInputType.keyboard()
    val visualTransformation =
        if (keyboard == KeyboardType.Password || keyboard == KeyboardType.NumberPassword){
            PasswordVisualTransformation()
        } else {
            VisualTransformation.None
        }

    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        DefaultComponentHeader(
            modifier = Modifier.fillMaxWidth(),
            title = component.componentTitle,
            description = component.componentDescription
        )

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = currentText,
            onValueChange = {
                currentText = it
                onChange(it)
            },
            singleLine = true,
            visualTransformation = visualTransformation,
            keyboardOptions = KeyboardOptions(
                keyboardType = keyboard
            )
        )
    }
}
