package com.example.components.dynamic_components.components.multiline.presentation

import android.annotation.SuppressLint
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
    private var startText: String = "",
    private val component: Component,
    private val onChange: (Boolean) -> Unit = {},
    private val onTextChange: (String) -> Unit = {}
) : BaseDynamicComponent {
    override fun getValue(): String = startText

    override fun isValid(): Boolean {
        return startText.length <= component.componentMaxLength && startText.isNotEmpty()
    }

    @Composable
    override fun getContent() {
         return CXCMultilineTextField(
            text = startText,
            component = component,
            onChange = {
                startText = it
                onChange(isValid())
                onTextChange(it)
            },
            key = component.componentId
        )
    }
}

@SuppressLint("UnrememberedMutableState")
@Composable
fun CXCMultilineTextField(
    text: String,
    component: Component,
    onChange: (String) -> Unit,
    key: Int
) {
    var currentText by remember { mutableStateOf("") }
    val errorMessage = if (currentText.length > component.componentMaxLength) "Limite de caracteres excedido" else ""

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
            label = { Text(text = component.componentLabel+key) }
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
