package com.example.components.feature.dynamic_form.domain.model

import com.example.components.dynamic_components.components.dropdown.getSingleCheckedOptionDescription
import com.example.components.dynamic_components.components.utils.enums.EnumComponentInputType
import com.example.components.dynamic_components.components.utils.enums.EnumComponentType

data class Component(
    val componentId: Int = 0,
    val componentTitle: String = "Componente",
    val componentDescription: String? = null,
    var componentValue: String? = null,
    val componentLabel: String = "Label",
    val componentType: EnumComponentType = EnumComponentType.TEXT_FIELD,
    val componentMaxLength: Int = 100,
    val componentInputType: EnumComponentInputType = EnumComponentInputType.TEXT,
    var componentOptions: List<Option> = emptyList(),
    var componentAttachments: List<Attachment> = emptyList(),
){
    fun getSingleCheckedOptionDescription(): String {
        return componentOptions.firstOrNull { it.optionChecked }?.optionDescription ?: ""
    }
}
