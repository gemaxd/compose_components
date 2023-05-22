@file:OptIn(ExperimentalAnimationApi::class)

package com.example.components.feature.dynamic_form.presentation.dynamic_form.wrapper

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.components.dynamic_components.components.DynamicComponentEvent
import com.example.components.dynamic_components.components.attachment.AttachmentComponentBasis
import com.example.components.dynamic_components.components.checkboxeslist.CheckBoxesListComponentBasis
import com.example.components.dynamic_components.components.chipgroup.ChipGroupComponentBasis
import com.example.components.dynamic_components.components.multilinetext.MultilineTextFieldBasis
import com.example.components.dynamic_components.components.dropdown.DropDownComponentBasis
import com.example.components.dynamic_components.components.radiobuttonslist.RadioButtonListComponentBasis
import com.example.components.dynamic_components.components.singlelinetext.TextFieldComponentBasis
import com.example.components.dynamic_components.components.utils.enums.EnumComponentType
import com.example.components.feature.dynamic_form.domain.model.Component

@OptIn(ExperimentalMaterialApi::class)
@ExperimentalLayoutApi
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

@ExperimentalLayoutApi
@ExperimentalMaterialApi
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

            EnumComponentType.CHECKBOX_LIST_FIELD -> {
                CheckBoxesListComponentBasis(
                    component = component,
                    onComponentEvent = onComponentEvent
                ).Content()
            }
            EnumComponentType.RADIOBUTTON_LIST_FIELD -> {
                RadioButtonListComponentBasis(
                    component = component,
                    onComponentEvent = onComponentEvent
                ).Content()
            }
            EnumComponentType.DROPDOWN_FIELD -> {
                DropDownComponentBasis(
                    component = component,
                    onComponentEvent = onComponentEvent
                ).Content()
            }
            EnumComponentType.CHIP_GROUP_FIELD -> {
                ChipGroupComponentBasis(
                    component = component,
                    onComponentEvent = onComponentEvent
                ).Content()
            }
        }
    }
}