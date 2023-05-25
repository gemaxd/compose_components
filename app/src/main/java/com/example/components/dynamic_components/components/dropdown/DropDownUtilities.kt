package com.example.components.dynamic_components.components.dropdown

import com.example.components.feature.dynamic_form.domain.model.Option

fun emptyCategory() =
    Option(optionCode = 0, optionDescription = "Selecione uma Categoria")

fun emptySubCategory() =
    Option(optionCode = 0, optionDescription = "Selecione uma SubCategoria")

fun emptyOption() =
    Option(optionCode = 0, optionDescription = "Selecione uma Opção")

fun List<Option>.getSingleCheckedOptionDescription(): String {
    return this.firstOrNull { it.optionChecked }?.optionDescription ?: ""
}