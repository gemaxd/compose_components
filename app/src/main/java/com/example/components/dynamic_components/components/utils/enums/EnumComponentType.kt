package com.example.components.dynamic_components.components.utils.enums

import androidx.compose.ui.text.input.KeyboardType

enum class EnumComponentType() {
    MULTILINE_TEXT_FIELD,
    TEXT_FIELD,
    ATTACHMENT_FIELD,
    DROPDOWN_FIELD,
    CHECKBOX_LIST_FIELD,
    RADIOBUTTON_LIST_FIELD,
    CHIP_GROUP_FIELD,
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