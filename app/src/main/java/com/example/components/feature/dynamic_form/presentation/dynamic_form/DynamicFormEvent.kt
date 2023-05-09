package com.example.components.feature.dynamic_form.presentation.dynamic_form

import com.example.components.feature.dynamic_form.domain.model.Component

sealed class DynamicFormEvent {
    object EnableFormSubmission: DynamicFormEvent()
    object DisableFormSubmission: DynamicFormEvent()
    data class CreateComponentList(val components: List<Component>): DynamicFormEvent()
    data class UpdateValidations(val component: Component, val isValid: Boolean): DynamicFormEvent()
    data class UpdateValues(val component: Component, val value: String): DynamicFormEvent()

}