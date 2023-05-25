package com.example.components.feature.dynamic_form.presentation.dynamic_form

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.components.dynamic_components.components.DynamicComponentEvent
import com.example.components.dynamic_components.components.dropdown.emptySubCategory
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

    fun onEvent(event: DynamicFormEvent) {
        when (event) {
            is DynamicFormEvent.EnableFormSubmission -> {
                dynamicFormStateUpdate {
                    isValid = true
                }
            }

            is DynamicFormEvent.DisableFormSubmission -> {
                dynamicFormStateUpdate {
                    isValid = false
                }
            }

            is DynamicFormEvent.LoadComponentList -> {
                viewModelScope.launch {
                    val components: List<Component> =
                        loadComponentsList(categoryId = event.categoryId)

                    val validations = components.map {
                        it to mutableStateOf(false)
                    }
                    dynamicFormStateUpdate {
                        this.isValid = false
                        this.components = components
                        this.validations = validations
                        this.isLoading = false
                    }
                }
            }

            is DynamicFormEvent.ClearComponentList -> {
                dynamicFormStateUpdate {
                    this.components = emptyList()
                    this.validations = emptyList()
                }
            }

            is DynamicFormEvent.LoadCategoriesList -> {
                dynamicFormStateUpdate {
                    this.categories = event.categories
                    this.isLoading = false
                }
            }

            is DynamicFormEvent.LoadSubCategoriesList -> {
                viewModelScope.launch {
                    val subCategoriesList = dynamicFormRepository.getSubcategories(event.categoryId)

                    dynamicFormStateUpdate {
                        this.components = emptyList()
                        this.validations = emptyList()
                        this.subcategories = subCategoriesList
                        this.isLoading = false
                    }
                }
            }

            is DynamicFormEvent.StartLoading -> {
                dynamicFormStateUpdate {
                    this.isLoading = true
                }
            }

            is DynamicFormEvent.StopLoading -> {
                dynamicFormStateUpdate {
                    this.isLoading = false
                }
            }

            is DynamicFormEvent.OnCategorySelection -> {
                dynamicFormStateUpdate {
                    selectedCategory = event.category
                    selectedSubCategory = emptySubCategory()
                }
            }

            is DynamicFormEvent.OnSubCategorySelection -> {
                dynamicFormStateUpdate {
                    selectedSubCategory = event.subCategory
                }
            }
        }
    }

    fun onComponentEvent(event: DynamicComponentEvent) {
        when (event) {
            is DynamicComponentEvent.OnDropDownOptionSelected -> {
                val options = event.component.componentOptions
                val selectedOption = event.option

                options.map { it.optionChecked = false }
                val optionSelected = options.find { it.optionCode == selectedOption.optionCode }
                optionSelected?.optionChecked = true
            }

            is DynamicComponentEvent.AddAttachment -> {
                val component = event.component
                component.componentAttachments =
                    component.componentAttachments.plus(event.attachment)
            }

            is DynamicComponentEvent.LoadAttachments -> {}

            is DynamicComponentEvent.CounterUpdate -> {}

            is DynamicComponentEvent.RemoveAttachment -> {
                val component = event.component
                component.componentAttachments =
                    component.componentAttachments.minus(event.attachment)
            }

            is DynamicComponentEvent.OnTextChange -> {
                val updatedValidations = _state.value.validations
                updatedValidations.find { it.first == event.component }?.second?.value =
                    event.isValid

                val updatedComponents = _state.value.components
                updatedComponents.find { it == event.component }?.componentValue = event.text

                dynamicFormStateUpdate {
                    this.validations = updatedValidations
                    this.components = updatedComponents
                    this.isValid = updatedValidations.all { it.second.value }
                }
            }

            is DynamicComponentEvent.OnChipAdd -> {
                val component = event.component
                component.componentOptions = component.componentOptions.plus(event.option)
            }

            is DynamicComponentEvent.OnChipRemove -> {
                val component = event.component
                component.componentOptions = component.componentOptions.minus(event.option)
            }

            is DynamicComponentEvent.UpdateOptionsValidations -> {
                updateOptionValidations(
                    component = event.component,
                    isValid = event.isValid
                )
            }

            is DynamicComponentEvent.OnRadioButtonSelection -> {
                val options = event.component.componentOptions
                val selectedOption = event.option

                options.map { it.optionChecked = false }
                val optionSelected = options.find { it.optionCode == selectedOption.optionCode }
                optionSelected?.optionChecked = true
            }

            is DynamicComponentEvent.OnCheckBoxToggle -> {
                val options = event.component.componentOptions
                val selectedOption = event.option

                options.find { it.optionCode == selectedOption.optionCode }?.apply {
                    this.optionChecked = !this.optionChecked
                }
            }
        }
    }

    private fun dynamicFormStateUpdate(properties: DynamicFormState.() -> Unit) {
        _state.value = _state.value.copy().apply(properties)
        dynamicFormRepository.updateComponents(_state.value.components)
    }

    private fun updateOptionValidations(
        component: Component,
        isValid: Boolean
    ) {
        val updatedValidations = _state.value.validations
        updatedValidations.find { it.first == component }?.second?.value = isValid

        dynamicFormStateUpdate {
            this.validations = updatedValidations
            this.isValid = updatedValidations.all { it.second.value }
        }
    }

    private suspend fun loadComponentsList(categoryId: Int = 0): List<Component> =
        suspendCoroutine { continuation ->
            onEvent(DynamicFormEvent.ClearComponentList)
            onEvent(DynamicFormEvent.DisableFormSubmission)
            onEvent(DynamicFormEvent.StartLoading)

            viewModelScope.launch {
                try {
                    val componentsList =
                        dynamicFormRepository.getComponentsBySubcategory(categoryId)
                    delay(1500)
                    continuation.resume(componentsList)
                } catch (e: Exception) {
                    continuation.resumeWithException(e)
                }
            }
        }
}