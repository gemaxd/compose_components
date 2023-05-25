package com.example.components.dynamic_components.components.base

import androidx.compose.runtime.Composable

interface BaseDynamicComponent {
    fun isValid(): Boolean
    @Composable
    fun Content()

    @Composable
    fun Review()
}