@file:OptIn(ExperimentalAnimationApi::class)

package com.example.components.feature.dynamic_form.presentation.dynamic_form.wrapper

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.components.dynamic_components.components.base.BaseDynamicComponent
import com.example.components.dynamic_components.components.multiline.presentation.MultilineTextFieldComponent
import com.example.components.dynamic_components.components.utils.EnumComponentType
import com.example.components.feature.dynamic_form.domain.model.Component
import com.example.components.feature.dynamic_form.presentation.dynamic_form.DynamicFormViewModel

@Composable
fun CreateComponents(
    components: List<Component>,
    onChangeValidation : (Component, Boolean) -> Unit,
    onValueChange: (Component, String) -> Unit
): List<BaseDynamicComponent> {
    components.forEach { component ->
        ChooseComponent(
            component = component,
            onChange = { isValid ->
                onChangeValidation(component, isValid)
            },
            onValueChange = { newValue ->
                onValueChange(component, newValue)
            }
        )
    }

    return transformIntoBaseComponents(components = components)
}

fun transformIntoBaseComponents(
    components: List<Component>
): List<BaseDynamicComponent> {
    return components.map {
        ChooseBaseComponent(component = it)
    }
}

fun ChooseBaseComponent(component: Component): BaseDynamicComponent {
    return when (component.componentType) {
        EnumComponentType.MULTILINE_TEXT_FIELD -> {
            MultilineTextFieldComponent( component = component )
        }

        EnumComponentType.TEXT_FIELD -> {
            MultilineTextFieldComponent(component = component)
        }
    }
}

@ExperimentalAnimationApi
@Composable
fun ChooseComponent(
    component: Component,
    onChange: (Boolean) -> Unit,
    onValueChange: (String) -> Unit
) {
    when (component.componentType) {
        EnumComponentType.MULTILINE_TEXT_FIELD -> {
            MultilineTextFieldComponent(
                component = component,
                onChange = {
                    onChange(it)
                },
                onTextChange = {
                    onValueChange(it)
                }
            ).getContent()
        }

        EnumComponentType.TEXT_FIELD -> {
            MultilineTextFieldComponent(component = component).getContent()
        }
    }
}