package com.example.components.feature.dynamic_form.domain.repository

import com.example.components.feature.dynamic_form.domain.model.Component
import com.example.components.feature.dynamic_form.domain.model.Option

interface DynamicFormRepository {
    fun updateComponents(components: List<Component>)
    suspend fun getComponents(): List<Component>
    fun getComponentsBySubcategory(subcategoryId: Int): List<Component>
    fun getCategories(): List<Option>
    fun getSubcategories(categoryId: Int): List<Option>
}