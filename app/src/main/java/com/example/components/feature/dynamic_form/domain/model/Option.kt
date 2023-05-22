package com.example.components.feature.dynamic_form.domain.model

data class Option(
    val optionCode: Int = 0,
    var optionChecked: Boolean = false,
    val optionDescription: String = ""
)