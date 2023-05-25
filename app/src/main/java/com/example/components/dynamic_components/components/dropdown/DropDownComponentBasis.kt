package com.example.components.dynamic_components.components.dropdown

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.example.components.dynamic_components.components.DynamicComponentEvent
import com.example.components.dynamic_components.components.base.BaseDynamicListComponent
import com.example.components.dynamic_components.components.singlelinetext.SimpleComponentReview
import com.example.components.feature.dynamic_form.domain.model.Component

@ExperimentalAnimationApi
class DropDownComponentBasis(
    private val component: Component,
    private val onComponentEvent: (DynamicComponentEvent) -> Unit = {},
) : BaseDynamicListComponent {

    val title = component.componentTitle
    val description = component.componentDescription
    val options = component.componentOptions

    override fun isValid(): Boolean {
        return component.componentOptions.any {
            it.optionChecked
        }
    }

    @Composable
    override fun Content() {
        val selectedOption = remember { mutableStateOf(emptyOption()) }

        LaunchedEffect(Unit){
            selectedOption.value = component.componentOptions.firstOrNull { it.optionChecked } ?: emptyOption()
        }

        DropdownContent(
            title = title,
            description = description,
            items = options,
            selectedItem = selectedOption.value,
            onItemSelected = { option ->
                selectedOption.value = option
                onComponentEvent(
                    DynamicComponentEvent.OnDropDownOptionSelected(
                        component, option
                    )
                )
                onComponentEvent(
                    DynamicComponentEvent.UpdateOptionsValidations(
                        component, option, isValid()
                    )
                )
            }
        )
    }

    @Composable
    override fun Review() {
        SimpleComponentReview(
            title = title,
            description = description,
            values = listOf(component.getSingleCheckedOptionDescription())
        )
    }
}

