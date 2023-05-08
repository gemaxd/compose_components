package com.example.components.dynamic_components.components.base

import androidx.compose.runtime.Composable

interface Validable {
    fun isValid(): Boolean
}

class BaseDynamicComponentImpl: BaseDynamicComponent {
    override fun getValue(): String {
        return ""
    }

    override fun isValid(): Boolean {
        return false
    }

    @Composable
    override fun getContent() { }

}

interface BaseDynamicComponent {

    fun getValue(): String

    fun isValid(): Boolean

    @Composable
    fun getContent()
}