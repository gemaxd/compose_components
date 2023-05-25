package com.example.components.dynamic_components.components.multilinetext

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import com.example.components.dynamic_components.components.DynamicComponentEvent
import com.example.components.dynamic_components.components.base.BaseDynamicComponent
import com.example.components.dynamic_components.components.singlelinetext.TextFieldReview
import com.example.components.feature.dynamic_form.domain.model.Component

@ExperimentalAnimationApi
class MultilineTextFieldBasis(
    private var startText: String = "",
    private val component: Component,
    private val onComponentEvent: (DynamicComponentEvent) -> Unit = {},
) : BaseDynamicComponent {

    override fun isValid(): Boolean {
        return startText.length <= component.componentMaxLength && startText.isNotEmpty()
    }

    @Composable
    override fun Content() {
         return MultilineTextFieldContent(
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

    @Composable
    override fun Review() {
        TextFieldReview(
            title = component.componentTitle,
            description = component.componentDescription,
            value = component.componentValue
        )
    }
}


