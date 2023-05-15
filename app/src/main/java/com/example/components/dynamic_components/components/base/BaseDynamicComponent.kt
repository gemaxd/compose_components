package com.example.components.dynamic_components.components.base

import androidx.compose.runtime.Composable

interface BaseDynamicComponent {
    fun getValue(): String
    fun isValid(): Boolean
    @Composable
    fun GetContent()
}