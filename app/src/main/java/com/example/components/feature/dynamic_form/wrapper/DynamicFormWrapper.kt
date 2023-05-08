@file:OptIn(ExperimentalAnimationApi::class)

package com.example.components.feature.dynamic_form.wrapper

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import com.example.components.dynamic_components.components.base.BaseDynamicComponent
import com.example.components.dynamic_components.components.multiline.presentation.MultilineTextFieldComponent
import com.example.components.dynamic_components.components.utils.EnumComponentType
import com.example.components.feature.model.Component

@Composable
fun CreateComponents(
    components: List<Component>,
    onComponentValidationChange: (Component, Boolean) -> Unit
) {
    val baseComponents = transformIntoBaseComponents(components = components)

    components.forEach {
        ChooseComponent(component = it) { isValid ->
            onComponentValidationChange(it, isValid)
            validateForm(baseComponents)
        }
    }
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

@Composable
fun ChooseComponent(component: Component, onChange: (Boolean) -> Unit) {
    when (component.componentType) {
        EnumComponentType.MULTILINE_TEXT_FIELD -> {
            MultilineTextFieldComponent(
                component = component,
                onChange = {
                    onChange(it)
                }
            ).getContent()
        }

        EnumComponentType.TEXT_FIELD -> {
            MultilineTextFieldComponent(component = component).getContent()
        }
    }
}

fun validateForm(components: List<BaseDynamicComponent>): Boolean {
    var formIsValid = true
    for (component in components) {
        if (!component.isValid()) {
            formIsValid = false
        }
    }
    return formIsValid
}