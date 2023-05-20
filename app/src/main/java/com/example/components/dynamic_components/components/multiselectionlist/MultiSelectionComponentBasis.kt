package com.example.components.dynamic_components.components.multiselectionlist

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.example.components.dynamic_components.components.DynamicComponentEvent
import com.example.components.dynamic_components.components.base.BaseDynamicListComponent
import com.example.components.feature.dynamic_form.domain.model.Component
import com.example.components.feature.dynamic_form.domain.model.Option

@ExperimentalAnimationApi
class MultiSelectionComponentBasis(
    private val component: Component,
    private val onComponentEvent: (DynamicComponentEvent) -> Unit,
) : BaseDynamicListComponent {
    override fun getValue(): List<Option> {
        return component.componentOptions.filter { it.optionChecked }
    }

    override fun isValid(): Boolean {
        return component.componentOptions.any { it.optionChecked }
    }

    @Composable
    override fun Content() {

    }
}

@Composable
fun MultiSelectionContent(
    component: Component
){
    val options = remember { mutableStateListOf(component.componentOptions) }

    Column(
        modifier = Modifier.fillMaxWidth()
    ) {

    }
}