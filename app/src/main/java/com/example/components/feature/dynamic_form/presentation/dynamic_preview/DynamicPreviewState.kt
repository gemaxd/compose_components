package com.example.components.feature.dynamic_form.presentation.dynamic_preview

import com.example.components.feature.dynamic_form.domain.model.Component

data class DynamicReviewState(
    var components: List<Component> = emptyList()
)