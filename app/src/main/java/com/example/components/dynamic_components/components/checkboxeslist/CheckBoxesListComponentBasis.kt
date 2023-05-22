package com.example.components.dynamic_components.components.checkboxeslist

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.components.dynamic_components.components.DynamicComponentEvent
import com.example.components.dynamic_components.components.base.BaseDynamicListComponent
import com.example.components.dynamic_components.components.dropdown.emptyOption
import com.example.components.dynamic_components.components.dropdown.toggleOption
import com.example.components.feature.dynamic_form.domain.model.Component
import com.example.components.feature.dynamic_form.domain.model.Option

@ExperimentalAnimationApi
class CheckBoxesListComponentBasis(
    private val component: Component,
    private val onComponentEvent: (DynamicComponentEvent) -> Unit,
) : BaseDynamicListComponent {
    override fun getValue(): List<Option> {
        return component.componentOptions.filter { it.optionChecked }
    }

    override fun isValid(): Boolean {
        return component.componentOptions.any {
            it.optionChecked
        }
    }

    @Composable
    override fun Content() {
        var selectedOption by remember { mutableStateOf(emptyOption()) }

        CheckBoxesListContent(
            title = component.componentTitle,
            description = component.componentDescription,
            items = component.componentOptions,
            onItemSelected = { option ->
                selectedOption = option
                component.componentOptions.toggleOption(option)
                onComponentEvent(
                    DynamicComponentEvent.OnDropDownOptionSelected(
                        component, option, isValid()
                    )
                )
            }
        )
    }
}

