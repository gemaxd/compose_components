@file:OptIn(ExperimentalAnimationApi::class)

package com.example.components.feature.dynamic_form.presentation.dynamic_form.wrapper

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.components.dynamic_components.components.DynamicComponentEvent
import com.example.components.dynamic_components.components.attachment.AttachmentComponentBasis
import com.example.components.dynamic_components.components.multilinetext.MultilineTextFieldBasis
import com.example.components.dynamic_components.components.singlelinetext.TextFieldComponentBasis
import com.example.components.dynamic_components.components.utils.EnumComponentType
import com.example.components.feature.dynamic_form.domain.model.Component

@Composable
fun CreateComponents(
    components: List<Component>,
    onComponentEvent: (DynamicComponentEvent) -> Unit
) {
    components.forEach { component ->
        ChooseComponent(
            component = component,
            onComponentEvent = onComponentEvent
        )
    }
}

@ExperimentalAnimationApi
@Composable
fun ChooseComponent(
    component: Component,
    onComponentEvent: (DynamicComponentEvent) -> Unit
) {
    Box(
        modifier = Modifier.padding(vertical = 8.dp)
    ) {
        when (component.componentType) {
            EnumComponentType.MULTILINE_TEXT_FIELD -> {
                MultilineTextFieldBasis(
                    component = component,
                    onComponentEvent = onComponentEvent
                ).Content()
            }

            EnumComponentType.TEXT_FIELD -> {
                TextFieldComponentBasis(
                    component = component,
                    onComponentEvent = onComponentEvent
                ).Content()
            }

            EnumComponentType.ATTACHMENT_FIELD -> {
                AttachmentComponentBasis(
                    component = component,
                    onComponentEvent = onComponentEvent
                ).Content()
            }

            EnumComponentType.MULTI_SELECTION_LIST_FIELD -> {}
            EnumComponentType.SINGLE_SELECTION_LIST_FIELD -> {}
        }
    }
}