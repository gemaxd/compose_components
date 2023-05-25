package com.example.components.feature.dynamic_form.presentation.dynamic_form

import androidx.compose.runtime.MutableState
import com.example.components.feature.dynamic_form.domain.model.Component
import com.example.components.feature.dynamic_form.domain.model.Option

data class DynamicFormState(
    var validations: List<Pair<Component, MutableState<Boolean>>> = emptyList(),
    var components: List<Component> = emptyList(),
    var categories: List<Option> = emptyList(),
    var subcategories: List<Option> = emptyList(),
    var isValid: Boolean = false,
    var isLoading: Boolean = false
)