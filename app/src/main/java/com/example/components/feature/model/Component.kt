package com.example.components.feature.model

import com.example.components.dynamic_components.components.utils.EnumComponentType

data class Component(
    val componentType: EnumComponentType = EnumComponentType.TEXT_FIELD,
    val componentMaxLength: Int = 100,
    val componentLabel: String = "Label"
){
    fun isValid(): Boolean { return true }

    fun getValue(): String { return "" }
}
