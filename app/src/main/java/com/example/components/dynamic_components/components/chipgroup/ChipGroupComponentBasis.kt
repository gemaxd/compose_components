package com.example.components.dynamic_components.components.chipgroup

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import com.example.components.dynamic_components.components.DynamicComponentEvent
import com.example.components.dynamic_components.components.base.BaseDynamicListComponent
import com.example.components.dynamic_components.components.singlelinetext.SimpleComponentReview
import com.example.components.dynamic_components.components.utils.enums.keyboard
import com.example.components.feature.dynamic_form.domain.model.Component

@ExperimentalMaterialApi
@ExperimentalLayoutApi
@ExperimentalAnimationApi
class ChipGroupComponentBasis(
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
        ChipGroupContent(
            title = component.componentTitle,
            description = component.componentDescription,
            options = component.componentOptions,
            keyboard = component.componentInputType.keyboard(),
            onChipAdd = {
                onComponentEvent(
                    DynamicComponentEvent.OnChipAdd(
                        component = component,
                        option = it
                    )
                )
                onComponentEvent(
                    DynamicComponentEvent.UpdateOptionsValidations(
                        component = component,
                        option = it,
                        isValid = isValid()
                    )
                )
            },
            onChipRemove = {
                onComponentEvent(
                    DynamicComponentEvent.OnChipRemove(
                        component = component,
                        option = it
                    )
                )
                onComponentEvent(
                    DynamicComponentEvent.UpdateOptionsValidations(
                        component = component,
                        option = it,
                        isValid = isValid()
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
            values = component.componentOptions.map { it.optionDescription }
        )
    }
}

