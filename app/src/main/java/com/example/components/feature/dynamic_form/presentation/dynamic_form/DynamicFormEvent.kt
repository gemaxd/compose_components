package com.example.components.feature.dynamic_form.presentation.dynamic_form

import com.example.components.feature.dynamic_form.domain.model.Component

sealed class DynamicFormEvent {
    object EnableFormSubmission: DynamicFormEvent()
    object DisableFormSubmission: DynamicFormEvent()
    data class LoadComponentList(val categoryId: Int): DynamicFormEvent()
    object ClearComponentList: DynamicFormEvent()
    data class LoadCategoriesList(val categories: List<Pair<Int, String>>): DynamicFormEvent()
    data class LoadSubCategoriesList(val categoryId: Int): DynamicFormEvent()
    object StartLoading: DynamicFormEvent()
    object StopLoading: DynamicFormEvent()


}