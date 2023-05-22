package com.example.components.dynamic_components.components.dropdown

import com.example.components.feature.dynamic_form.domain.model.Component
import com.example.components.feature.dynamic_form.domain.model.Option

fun emptyCategory() =
    Option(optionCode = 0, optionDescription = "Selecione uma Categoria")

fun emptySubCategory() =
    Option(optionCode = 0, optionDescription = "Selecione uma SubCategoria")

fun emptyOption() =
    Option(optionCode = 0, optionDescription = "Selecione uma Opção")


fun List<Option>.singleCheckOption(option: Option) {
    this.map { it.optionChecked = false }
    val optionSelected = this.find { it.optionCode == option.optionCode }
    optionSelected?.optionChecked = true
}

fun List<Option>.toggleOption(option: Option) {
    val optionSelected = this.find { it.optionCode == option.optionCode }
    val currentValue = optionSelected!!.optionChecked
    optionSelected.optionChecked = !currentValue
}