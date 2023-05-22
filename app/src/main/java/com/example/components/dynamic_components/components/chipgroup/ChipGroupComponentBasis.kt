package com.example.components.dynamic_components.components.chipgroup

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import com.example.components.dynamic_components.components.DynamicComponentEvent
import com.example.components.dynamic_components.components.base.BaseDynamicListComponent
import com.example.components.dynamic_components.components.utils.enums.keyboard
import com.example.components.feature.dynamic_form.domain.model.Component
import com.example.components.feature.dynamic_form.domain.model.Option

@ExperimentalMaterialApi
@ExperimentalLayoutApi
@ExperimentalAnimationApi
class ChipGroupComponentBasis(
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
        ChipGroupContent(
            title = component.componentTitle,
            description = component.componentDescription,
            keyboard = component.componentInputType.keyboard(),
            onChipAdd = {
                component.componentOptions = component.componentOptions + it
                onComponentEvent(
                    DynamicComponentEvent.OnChipAdd(
                        component = component,
                        option = it,
                        isValid = isValid()
                    )
                )
            },
            onChipRemove = {
                component.componentOptions = component.componentOptions - it
                onComponentEvent(
                    DynamicComponentEvent.OnChipRemove(
                        component = component,
                        option = it,
                        isValid = isValid()
                    )
                )
            }
        )
    }
}

