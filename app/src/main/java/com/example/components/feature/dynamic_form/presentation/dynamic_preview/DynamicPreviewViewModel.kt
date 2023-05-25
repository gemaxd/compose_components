package com.example.components.feature.dynamic_form.presentation.dynamic_preview

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.components.feature.dynamic_form.domain.repository.DynamicFormRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DynamicReviewViewModel @Inject constructor(
    private val dynamicFormRepository: DynamicFormRepository
): ViewModel() {

    private val _state = MutableStateFlow(DynamicReviewState())
    val state: Flow<DynamicReviewState> = _state

    init {
        onEvent(
            DynamicReviewEvent.OnComponentsLoad
        )
    }

     private fun dynamicFormStateUpdate(properties: DynamicReviewState.() -> Unit){
        _state.value = _state.value.copy().apply(properties)
        dynamicFormRepository.updateComponents(_state.value.components)
    }

    fun onEvent(event: DynamicReviewEvent){
        when(event){
            is DynamicReviewEvent.OnComponentsLoad -> {
                dynamicFormStateUpdate {
                    viewModelScope.launch {
                        components = dynamicFormRepository.getComponents()
                    }
                }
            }
        }
    }
}