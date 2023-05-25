package com.example.components.feature.dynamic_form.presentation.dynamic_form

import androidx.compose.runtime.MutableState
import com.example.components.dynamic_components.components.dropdown.emptyCategory
import com.example.components.dynamic_components.components.dropdown.emptyOption
import com.example.components.dynamic_components.components.dropdown.emptySubCategory
import com.example.components.feature.dynamic_form.domain.model.Component
import com.example.components.feature.dynamic_form.domain.model.Option

data class DynamicFormState(
    var validations: List<Pair<Component, MutableState<Boolean>>> = emptyList(),
    var components: List<Component> = emptyList(),
    var categories: List<Option> = emptyList(),
    var selectedCategory: Option = emptyCategory(),
    var subcategories: List<Option> = emptyList(),
    var selectedSubCategory: Option = emptySubCategory(),
    var isValid: Boolean = false,
    var isLoading: Boolean = false
)