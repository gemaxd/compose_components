package com.example.components.feature.dynamic_form.domain.repository

import com.example.components.feature.dynamic_form.domain.model.Component

interface DynamicFormRepository {
    fun getComponents(subcategoryId: Int): List<Component>
    fun getCategories(): List<Pair<Int, String>>
    fun getSubcategories(categoryId: Int): List<Pair<Int, String>>
}