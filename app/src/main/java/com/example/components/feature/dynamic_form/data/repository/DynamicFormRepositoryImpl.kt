package com.example.components.feature.dynamic_form.data.repository

import com.example.components.dynamic_components.components.utils.EnumComponentType
import com.example.components.feature.dynamic_form.domain.model.Component
import com.example.components.feature.dynamic_form.domain.repository.DynamicFormRepository

class DynamicFormRepositoryImpl: DynamicFormRepository {
    override fun getComponents(categoryId: Int): List<Component> {
        return when(categoryId){
            2 -> loadSecondList()
            3 -> loadThirdList()
            else -> loadFirstList()
        }
    }

    override fun getCategories(): List<Pair<Int, String>> {
        return listOf(
            Pair(1, "Item 1"),
            Pair(2, "Item 2"),
            Pair(3, "Item 3")
        )
    }

    private fun loadFirstList(): List<Component>{
        return listOf(
            Component(
                componentId = 11,
                componentType = EnumComponentType.MULTILINE_TEXT_FIELD,
                componentLabel = "First List Multiline",
                componentMaxLength = 75
            ),
            Component(
                componentId = 12,
                componentType = EnumComponentType.TEXT_FIELD,
                componentLabel = "First List Text"
            )
        )
    }

    private fun loadSecondList(): List<Component>{
        return listOf(
            Component(
                componentId = 21,
                componentType = EnumComponentType.TEXT_FIELD,
                componentLabel = "Second List Text 1"
            ),
            Component(
                componentId = 22,
                componentType = EnumComponentType.TEXT_FIELD,
                componentLabel = "Second List Text 2"
            ),
            Component(
                componentId = 23,
                componentType = EnumComponentType.TEXT_FIELD,
                componentLabel = "Second List Text 3"
            )
        )
    }

    private fun loadThirdList(): List<Component>{
        return listOf(
            Component(
                componentId = 31,
                componentType = EnumComponentType.MULTILINE_TEXT_FIELD,
                componentLabel = "Third List Multiline 1",
                componentMaxLength = 75
            ),
            Component(
                componentId = 32,
                componentType = EnumComponentType.MULTILINE_TEXT_FIELD,
                componentLabel = "Third List Multiline 2",
                componentMaxLength = 150
            ),
            Component(
                componentId = 33,
                componentType = EnumComponentType.MULTILINE_TEXT_FIELD,
                componentLabel = "Third List Multiline 3",
                componentMaxLength = 110
            ),
            Component(
                componentId = 34,
                componentType = EnumComponentType.TEXT_FIELD,
                componentLabel = "Third List Text"
            )
        )
    }
}