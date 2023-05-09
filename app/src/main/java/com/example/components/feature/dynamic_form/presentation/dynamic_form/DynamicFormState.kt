package com.example.components.feature.dynamic_form.presentation.dynamic_form

import androidx.compose.runtime.MutableState
import com.example.components.feature.dynamic_form.domain.model.Component

data class DynamicFormState(
    val values: List<Pair<Component, MutableState<String>>> = emptyList(),
    val validations: List<Pair<Component, MutableState<Boolean>>> = emptyList(),
    val components: List<Component> = emptyList(),
    val isValid: Boolean = false
)