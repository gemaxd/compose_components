package com.example.components.feature.dynamic_form.presentation.dynamic_preview

sealed class DynamicReviewEvent {
    object OnComponentsLoad: DynamicReviewEvent()
}
