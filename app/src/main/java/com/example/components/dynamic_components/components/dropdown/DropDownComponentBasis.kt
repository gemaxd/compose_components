package com.example.components.dynamic_components.components.dropdown

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.components.dynamic_components.components.DynamicComponentEvent
import com.example.components.dynamic_components.components.base.BaseDynamicListComponent
import com.example.components.feature.dynamic_form.domain.model.Component
import com.example.components.feature.dynamic_form.domain.model.Option

@ExperimentalAnimationApi
class DropDownComponentBasis(
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

        DropdownContent(
            title = component.componentTitle,
            description = component.componentDescription,
            items = component.componentOptions,
            selectedItem = selectedOption,
            onItemSelected = { option ->
                selectedOption = option
                component.componentOptions.singleCheckOption(option)
                onComponentEvent(
                    DynamicComponentEvent.OnDropDownOptionSelected(
                        component, option, isValid()
                    )
                )
            }
        )
    }
}

