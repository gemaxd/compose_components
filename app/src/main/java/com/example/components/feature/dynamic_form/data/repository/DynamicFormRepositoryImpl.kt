package com.example.components.feature.dynamic_form.data.repository

import com.example.components.dynamic_components.components.utils.enums.EnumComponentInputType
import com.example.components.dynamic_components.components.utils.enums.EnumComponentType
import com.example.components.feature.dynamic_form.domain.model.Component
import com.example.components.feature.dynamic_form.domain.model.Option
import com.example.components.feature.dynamic_form.domain.repository.DynamicFormRepository

class DynamicFormRepositoryImpl: DynamicFormRepository {
    override fun getComponents(subcategoryId: Int): List<Component> {
        return when(subcategoryId){
            2 -> loadSecondList()
            3 -> loadThirdList()
            else -> loadFirstList()
        }
    }

    override fun getCategories(): List<Option> {
        return listOf(
            Option(optionCode = 1, optionDescription = "Categoria #01"),
            Option(optionCode = 2, optionDescription = "Categoria #02"),
            Option(optionCode = 3, optionDescription = "Categoria #03")
        )
    }

    override fun getSubcategories(categoryId: Int): List<Option> {
        return loadSubcategoryList(categoryId)
    }

    private fun loadSubcategoryList(categoryId: Int): List<Option> {
        return when(categoryId){
            2 -> listOf(
                Option(optionCode = 4, optionDescription = "Sub-Categoria #04"),
                Option(optionCode = 5, optionDescription = "Sub-Categoria #05"),
                Option(optionCode = 6, optionDescription = "Sub-Categoria #06")
            )
            3 -> listOf(
                Option(optionCode = 7, optionDescription = "Sub-Categoria #07"),
                Option(optionCode = 8, optionDescription = "Sub-Categoria #08"),
                Option(optionCode = 9, optionDescription = "Sub-Categoria #09")
            )
            else -> listOf(
                Option(optionCode = 1, optionDescription = "Sub-Categoria #01"),
                Option(optionCode = 2, optionDescription = "Sub-Categoria #02"),
                Option(optionCode = 3, optionDescription = "Sub-Categoria #03")
            )
        }
    }

    private fun loadFirstList(): List<Component>{
        return listOf(
            Component(
                componentId = 10,
                componentType = EnumComponentType.TEXT_FIELD,
                componentLabel = "First List Text",
                componentInputType = EnumComponentInputType.NUMBER_PASSWORD,
                componentTitle = "NUMBER PASSWORD Component",
                componentDescription = "Breve descrição do campo com algumas observações de preenchimento."
            ),
            Component(
                componentId = 11,
                componentType = EnumComponentType.MULTILINE_TEXT_FIELD,
                componentLabel = "First List Text",
                componentInputType = EnumComponentInputType.NUMBER_PASSWORD,
                componentTitle = "NUMBER PASSWORD Component",
                componentDescription = "Breve descrição do campo com algumas observações de preenchimento."
            ),
            Component(
                componentId = 12,
                componentType = EnumComponentType.ATTACHMENT_FIELD,
                componentLabel = "First List Multiline",
                componentTitle = "Attachment Component",
                componentDescription = "Breve descrição do campo com algumas observações de preenchimento."
            ),
            Component(
                componentId = 13,
                componentType = EnumComponentType.DROPDOWN_FIELD,
                componentLabel = "First List Drop down",
                componentTitle = "DropDown Component",
                componentDescription = "Breve descrição do campo com algumas observações de preenchimento.",
                componentOptions = listOf(
                    Option(
                        optionCode = 1,
                        optionDescription = "Primeira opção"
                    ),
                    Option(
                        optionCode = 2,
                        optionDescription = "Segunda opção"
                    ),
                    Option(
                        optionCode = 3,
                        optionDescription = "Terceira opção"
                    ),
                    Option(
                        optionCode = 4,
                        optionDescription = "Quarta opção"
                    ),
                    Option(
                        optionCode = 5,
                        optionDescription = "Quinta opção"
                    )
                )
            ),
            Component(
                componentId = 14,
                componentType = EnumComponentType.CHECKBOX_LIST_FIELD,
                componentLabel = "First CheckBox List",
                componentTitle = "CheckBox Component",
                componentDescription = "Breve descrição do campo com algumas observações de preenchimento.",
                componentOptions = listOf(
                    Option(
                        optionCode = 1,
                        optionDescription = "Primeira checkbox"
                    ),
                    Option(
                        optionCode = 2,
                        optionDescription = "Segunda checkbox"
                    ),
                    Option(
                        optionCode = 3,
                        optionDescription = "Terceira checkbox"
                    )
                )
            ),
            Component(
                componentId = 15,
                componentType = EnumComponentType.RADIOBUTTON_LIST_FIELD,
                componentLabel = "First RadioButton List",
                componentTitle = "RadioGroup Component",
                componentDescription = "Breve descrição do campo com algumas observações de preenchimento.",
                componentOptions = listOf(
                    Option(
                        optionCode = 1,
                        optionDescription = "Primeira radio"
                    ),
                    Option(
                        optionCode = 2,
                        optionDescription = "Segunda radio"
                    ),
                    Option(
                        optionCode = 3,
                        optionDescription = "Terceira radio"
                    )
                )
            ),
            Component(
                componentId = 16,
                componentType = EnumComponentType.CHIP_GROUP_FIELD,
                componentLabel = "First Chip Group",
                componentTitle = "ChipGroup Component",
                componentDescription = "Breve descrição do campo com algumas observações de preenchimento.",
                componentOptions = emptyList()
            )


        )
    }

    private fun loadSecondList(): List<Component>{
        return listOf(
            Component(
                componentId = 21,
                componentType = EnumComponentType.TEXT_FIELD,
                componentLabel = "Second List Text 1",
                componentInputType = EnumComponentInputType.PHONE,
                componentTitle = "Single-line Component",
                componentDescription = "Breve descrição do campo com algumas observações de preenchimento."
            ),
            Component(
                componentId = 22,
                componentType = EnumComponentType.TEXT_FIELD,
                componentLabel = "Second List Text 2",
                componentInputType = EnumComponentInputType.ASCII,
                componentTitle = "Single-line Component",
                componentDescription = "Breve descrição do campo com algumas observações de preenchimento."
            ),
            Component(
                componentId = 23,
                componentType = EnumComponentType.TEXT_FIELD,
                componentLabel = "Second List Text 3",
                componentInputType = EnumComponentInputType.URI,
                componentTitle = "Single-line Component",
                componentDescription = "Breve descrição do campo com algumas observações de preenchimento."
            )
        )
    }

    private fun loadThirdList(): List<Component>{
        return listOf(
            Component(
                componentId = 31,
                componentType = EnumComponentType.MULTILINE_TEXT_FIELD,
                componentLabel = "Third List Multiline 1",
                componentMaxLength = 75,
                componentInputType = EnumComponentInputType.TEXT,
                componentTitle = "Multiline Component",
                componentDescription = "Breve descrição do campo com algumas observações de preenchimento."
            ),
            Component(
                componentId = 32,
                componentType = EnumComponentType.MULTILINE_TEXT_FIELD,
                componentLabel = "Third List Multiline 2",
                componentMaxLength = 150,
                componentInputType = EnumComponentInputType.EMAIL,
                componentTitle = "Multiline Component",
                componentDescription = "Breve descrição do campo com algumas observações de preenchimento."
            ),
            Component(
                componentId = 33,
                componentType = EnumComponentType.MULTILINE_TEXT_FIELD,
                componentLabel = "Third List Multiline 3",
                componentMaxLength = 110,
                componentInputType = EnumComponentInputType.DECIMAL,
                componentTitle = "Multiline Component",
                componentDescription = "Breve descrição do campo com algumas observações de preenchimento."
            ),
            Component(
                componentId = 34,
                componentType = EnumComponentType.TEXT_FIELD,
                componentLabel = "Third List Text",
                componentTitle = "Single-line Component",
                componentDescription = "Breve descrição do campo com algumas observações de preenchimento."
            ),
            Component(
                componentId = 35,
                componentType = EnumComponentType.MULTILINE_TEXT_FIELD,
                componentLabel = "Third List Multiline 3",
                componentMaxLength = 110,
                componentInputType = EnumComponentInputType.DECIMAL,
                componentTitle = "Multiline Component",
                componentDescription = "Breve descrição do campo com algumas observações de preenchimento."
            ),
            Component(
                componentId = 36,
                componentType = EnumComponentType.MULTILINE_TEXT_FIELD,
                componentLabel = "Third List Multiline 3",
                componentMaxLength = 110,
                componentInputType = EnumComponentInputType.DECIMAL,
                componentTitle = "Multiline Component",
                componentDescription = "Breve descrição do campo com algumas observações de preenchimento."
            ),
        )
    }
}