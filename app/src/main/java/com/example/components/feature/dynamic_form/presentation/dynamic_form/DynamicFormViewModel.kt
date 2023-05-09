package com.example.components.feature.dynamic_form.presentation.dynamic_form

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.components.dynamic_components.components.utils.EnumComponentType
import com.example.components.feature.dynamic_form.domain.model.Component
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class DynamicFormViewModel : ViewModel() {

    private val _state = MutableStateFlow(DynamicFormState())
    val state: Flow<DynamicFormState> = _state
    init {
        viewModelScope.launch {
            val createdComponents = createComponentsList()
            onEvent(
                DynamicFormEvent.CreateComponentList(
                    createdComponents
                )
            )
        }
    }

    fun onEvent(event: DynamicFormEvent){
        when(event){
            is DynamicFormEvent.EnableFormSubmission -> {
                _state.value = _state.value.copy(
                    isValid = true
                )
            }

            is DynamicFormEvent.DisableFormSubmission -> {
                _state.value = _state.value.copy(
                    isValid = false
                )
            }

            is DynamicFormEvent.CreateComponentList -> {
                val validations = event.components.map {
                    it to mutableStateOf(false)
                }

                val values = event.components.map {
                    it to mutableStateOf("")
                }

                _state.value = _state.value.copy(
                    components = event.components,
                    validations = validations,
                    values = values
                )
            }

            is DynamicFormEvent.UpdateValidations -> {
                val updatedValidations = _state.value.validations
                updatedValidations.find { it.first == event.component }?.second?.value = event.isValid

                _state.value = _state.value.copy(
                    validations = updatedValidations,
                    isValid = updatedValidations.all { it.second.value }
                )
            }

            is DynamicFormEvent.UpdateValues -> {
                val updatedValues = _state.value.values.toMutableList()
                updatedValues.find { it.first == event.component }?.second?.value = event.value

                _state.value = _state.value.copy(
                    values = updatedValues
                )
            }
        }
    }

    private fun createComponentsList() = listOf(
        Component(
            componentType = EnumComponentType.MULTILINE_TEXT_FIELD,
            componentLabel = "MultiLine",
            componentMaxLength = 150
        ),
        Component(
            componentType = EnumComponentType.MULTILINE_TEXT_FIELD,
            componentLabel = "MultiLine 02",
            componentMaxLength = 300
        )
    )

    fun prepareContentMessage(): String {
        var returnMessage = ""

        _state.value.values.forEach {
            returnMessage += " - ${it.second.value} - "
        }

        return returnMessage
    }

}