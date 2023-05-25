package com.example.components.dynamic_components.components.radiobuttonslist

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import com.example.components.dynamic_components.components.DynamicComponentEvent
import com.example.components.dynamic_components.components.base.BaseDynamicListComponent
import com.example.components.dynamic_components.components.singlelinetext.SimpleComponentReview
import com.example.components.feature.dynamic_form.domain.model.Component

@ExperimentalAnimationApi
class RadioButtonListComponentBasis(
    private val component: Component,
    private val onComponentEvent: (DynamicComponentEvent) -> Unit = {},
) : BaseDynamicListComponent {

    override fun isValid(): Boolean {
        return component.componentOptions.any {
            it.optionChecked
        }
    }

    @Composable
    override fun Content() {
        RadioButtonListContent(
            title = component.componentTitle,
            description = component.componentDescription,
            items = component.componentOptions,
            onItemSelected = { option ->
                onComponentEvent(
                    DynamicComponentEvent.OnRadioButtonSelection(
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
            title = component.componentTitle,
            description = component.componentDescription,
            values = listOf(component.getSingleCheckedOptionDescription())
        )
    }
}