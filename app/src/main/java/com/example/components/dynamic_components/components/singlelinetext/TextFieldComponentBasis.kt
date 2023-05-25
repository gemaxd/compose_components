package com.example.components.dynamic_components.components.singlelinetext

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.example.components.dynamic_components.components.DynamicComponentEvent
import com.example.components.dynamic_components.components.base.BaseDynamicComponent
import com.example.components.dynamic_components.components.utils.enums.keyboard
import com.example.components.feature.dynamic_form.domain.model.Component

@ExperimentalAnimationApi
class TextFieldComponentBasis(
    private val component: Component,
    private val onComponentEvent: (DynamicComponentEvent) -> Unit = {},
) : BaseDynamicComponent {
    private var text by mutableStateOf("")

    override fun isValid(): Boolean {
        return text.length <= component.componentMaxLength && text.isNotEmpty()
    }

    @Composable
    override fun Content() {
        return TextFieldContent(
            title = component.componentTitle,
            description = component.componentDescription,
            keyboard = component.componentInputType.keyboard(),
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

    @Composable
    override fun Review() {
        SimpleComponentReview(
            title = component.componentTitle,
            description = component.componentDescription,
            values = listOf(component.componentValue)
        )
    }
}


