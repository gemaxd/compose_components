package com.example.components.dynamic_components.components.utils

import androidx.compose.ui.text.input.KeyboardType

enum class EnumComponentType(val type: String) {
    MULTILINE_TEXT_FIELD (type = "multiline_text_field"),
    TEXT_FIELD (type = "text_field"),
    ATTACHMENT_FIELD (type = "attachment_field"),
    MULTI_SELECTION_LIST_FIELD (type = "multi_selection_list_field"),
    SINGLE_SELECTION_LIST_FIELD (type = "single_selection_list_field"),
}

enum class EnumComponentInputType(val type: Int) {
    TEXT (type = 1),
    ASCII (type = 2),
    NUMBER(type = 3),
    PHONE(type = 4),
    URI(type = 5),
    EMAIL(type = 6),
    PASSWORD(type = 7),
    NUMBER_PASSWORD(type = 8),
    DECIMAL(type = 9)
}

fun EnumComponentInputType.keyboard(): KeyboardType {
    return when(this){
        EnumComponentInputType.TEXT -> KeyboardType.Text
        EnumComponentInputType.ASCII -> KeyboardType.Ascii
        EnumComponentInputType.NUMBER -> KeyboardType.Number
        EnumComponentInputType.PHONE -> KeyboardType.Phone
        EnumComponentInputType.URI -> KeyboardType.Uri
        EnumComponentInputType.EMAIL -> KeyboardType.Email
        EnumComponentInputType.PASSWORD -> KeyboardType.Password
        EnumComponentInputType.NUMBER_PASSWORD -> KeyboardType.NumberPassword
        EnumComponentInputType.DECIMAL -> KeyboardType.Decimal
    }
}