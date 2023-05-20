package com.example.components.dynamic_components.components.base

import androidx.compose.runtime.Composable
import com.example.components.feature.dynamic_form.domain.model.Option

interface BaseDynamicListComponent {
    fun getValue(): List<Option>
    fun isValid(): Boolean
    @Composable
    fun Content()
}