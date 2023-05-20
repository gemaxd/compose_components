package com.example.components.feature.dynamic_form.data.repository

import com.example.components.dynamic_components.components.utils.EnumComponentInputType
import com.example.components.dynamic_components.components.utils.EnumComponentType
import com.example.components.feature.dynamic_form.domain.model.Component
import com.example.components.feature.dynamic_form.domain.repository.DynamicFormRepository

class DynamicFormRepositoryImpl: DynamicFormRepository {
    override fun getComponents(subcategoryId: Int): List<Component> {
        return when(subcategoryId){
            2 -> loadSecondList()
            3 -> loadThirdList()
            else -> loadFirstList()
        }
    }

    override fun getCategories(): List<Pair<Int, String>> {
        return listOf(
            Pair(1, "Categoria #01"),
            Pair(2, "Categoria #02"),
            Pair(3, "Categoria #03")
        )
    }

    override fun getSubcategories(categoryId: Int): List<Pair<Int, String>> {
        return loadSubcategoryList(categoryId)
    }

    private fun loadSubcategoryList(categoryId: Int): List<Pair<Int, String>> {
        return when(categoryId){
            2 -> listOf(
                Pair(4, "Sub-Categoria #04"),
                Pair(5, "Sub-Categoria #05"),
                Pair(6, "Sub-Categoria #06")
            )
            3 -> listOf(
                Pair(7, "Sub-Categoria #07"),
                Pair(8, "Sub-Categoria #08"),
                Pair(9, "Sub-Categoria #09")
            )
            else -> listOf(
                Pair(1, "Sub-Categoria #01"),
                Pair(2, "Sub-Categoria #02"),
                Pair(3, "Sub-Categoria #03")
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