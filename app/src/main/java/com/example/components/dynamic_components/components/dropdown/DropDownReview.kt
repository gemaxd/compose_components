package com.example.components.dynamic_components.components.dropdown

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.components.dynamic_components.components.base.DefaultComponentHeader
import com.example.components.dynamic_components.components.base.DynamicComponentContainer
import com.example.components.dynamic_components.components.base.ReviewDefaultBottomDivider

@Composable
fun DropDownReview(
    title: String,
    description: String?,
    value: String
) {
    DynamicComponentContainer(
        header = {
            DefaultComponentHeader(title = title, description = description)
        },
        content = {
            Text(text = value)
        },
        footer = {
            ReviewDefaultBottomDivider()
        }
    )
}