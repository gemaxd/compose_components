package com.example.components.feature.dynamic_form

import androidx.compose.runtime.Composable
import com.example.components.dynamic_components.components.base.BaseDynamicComponent

data class DynamicFormState(
    val dynamicFormComponents: List<BaseDynamicComponent> = emptyList()
)