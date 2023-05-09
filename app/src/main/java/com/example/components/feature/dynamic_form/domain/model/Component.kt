package com.example.components.feature.dynamic_form.domain.model

import com.example.components.dynamic_components.components.utils.EnumComponentType

data class Component(
    val componentType: EnumComponentType = EnumComponentType.TEXT_FIELD,
    val componentMaxLength: Int = 100,
    val componentLabel: String = "Label"
)
