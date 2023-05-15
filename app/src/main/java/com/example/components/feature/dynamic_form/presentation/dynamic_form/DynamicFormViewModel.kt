package com.example.components.feature.dynamic_form.presentation.dynamic_form

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.components.feature.dynamic_form.domain.model.Component
import com.example.components.feature.dynamic_form.domain.repository.DynamicFormRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

@HiltViewModel
class DynamicFormViewModel @Inject constructor(
    private val dynamicFormRepository: DynamicFormRepository
) : ViewModel() {

    private val _state = MutableStateFlow(DynamicFormState())
    val state: Flow<DynamicFormState> = _state

    init {
        onEvent(DynamicFormEvent.StartLoading)
        viewModelScope.launch {
            val categories = dynamicFormRepository.getCategories()

            onEvent(
                DynamicFormEvent.LoadCategoriesList(
                    categories
                )
            )
        }
    }

    fun onEvent(event: DynamicFormEvent){
        when(event){
            is DynamicFormEvent.EnableFormSubmission -> {
                emitState(_state.value.copy(
                    isValid = true
                ))
            }

            is DynamicFormEvent.DisableFormSubmission -> {
                emitState(_state.value.copy(
                    isValid = false
                ))
            }

            is DynamicFormEvent.LoadComponentList -> {

                onEvent(DynamicFormEvent.DisableFormSubmission)

                viewModelScope.launch {
                    val components: List<Component> =
                        loadComponentsList(categoryId = event.categoryId)

                    val validations = components.map {
                        it to mutableStateOf(false)
                    }

                    val values = components.map {
                        it to mutableStateOf("")
                    }

                    emitState(
                        _state.value.copy(
                            components = components,
                            validations = validations,
                            values = values,
                            isLoading = false
                        )
                    )
                }
            }

            is DynamicFormEvent.UpdateValidations -> {
                val updatedValidations = _state.value.validations
                updatedValidations.find { it.first == event.component }?.second?.value = event.isValid

                emitState(_state.value.copy(
                    validations = updatedValidations,
                    isValid = updatedValidations.all { it.second.value }
                ))
            }

            is DynamicFormEvent.UpdateValues -> {
                val updatedValues = _state.value.values
                updatedValues.find { it.first == event.component }?.second?.value = event.value

                emitState(_state.value.copy(
                    values = updatedValues
                ))
            }

            is DynamicFormEvent.ClearComponentList -> {
                emitState(_state.value.copy(
                    components = emptyList(),
                    validations = emptyList(),
                    values = emptyList()
                ))
            }

            is DynamicFormEvent.LoadCategoriesList -> {
                emitState(
                    _state.value.copy(
                        categories = event.categories,
                        isLoading = false
                    )
                )
            }

            is DynamicFormEvent.StartLoading -> {
                emitState(
                    _state.value.copy(
                        isLoading = true
                    )
                )
            }

            is DynamicFormEvent.StopLoading -> {
                emitState(
                    _state.value.copy(
                        isLoading = false
                    )
                )
            }
        }
    }

    private fun emitState(state: DynamicFormState) {
        viewModelScope.launch {
            _state.emit(state)
        }
    }

    fun prepareContentMessage(): String {
        var returnMessage = ""

        _state.value.values.forEach {
            returnMessage += " - ${it.second.value} - \n"
        }

        return returnMessage
    }

    private suspend fun loadComponentsList(categoryId: Int = 0): List<Component> = suspendCoroutine { continuation ->
        onEvent(DynamicFormEvent.ClearComponentList)
        onEvent(DynamicFormEvent.DisableFormSubmission)
        onEvent(DynamicFormEvent.StartLoading)

        viewModelScope.launch {
            try {
                val componentsList = dynamicFormRepository.getComponents(categoryId)
                delay(1500)
                continuation.resume(componentsList)
            } catch (e: Exception) {
                continuation.resumeWithException(e)
            }
        }
    }
}