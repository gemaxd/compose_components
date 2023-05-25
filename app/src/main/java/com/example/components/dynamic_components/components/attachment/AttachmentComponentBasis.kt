package com.example.components.dynamic_components.components.attachment

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.example.components.dynamic_components.components.DynamicComponentEvent
import com.example.components.dynamic_components.components.base.BaseDynamicComponent
import com.example.components.feature.dynamic_form.domain.model.Component

@ExperimentalAnimationApi
class AttachmentComponentBasis(
    private val component: Component,
    private val onComponentEvent: (DynamicComponentEvent) -> Unit = {}
) : BaseDynamicComponent {
    private var text by mutableStateOf("")

    override fun isValid(): Boolean {
        return text.length <= component.componentMaxLength && text.isNotEmpty()
    }

    @Composable
    override fun Content() {
        return AttachmentFieldContent(
            text = text,
            component = component,
            onTextChange = {
                text = it
                onComponentEvent(
                    DynamicComponentEvent.OnTextChange(
                        component, it, isValid()
                    )
                )
            },
            onAttachmentAdd = {
                onComponentEvent(
                    DynamicComponentEvent.AddAttachment(component, it)
                )
            },
            onAttachmentRemove = {
                onComponentEvent(
                    DynamicComponentEvent.RemoveAttachment(component, it)
                )
            }
        )
    }

    @Composable
    override fun Review() {
        AttachmentFieldReview(
            title = component.componentTitle,
            description = component.componentDescription,
            items = component.componentAttachments
        )
    }

}