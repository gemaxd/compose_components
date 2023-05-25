package com.example.components.dynamic_components.components.radiobuttonslist

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.components.dynamic_components.components.base.DefaultComponentHeader
import com.example.components.dynamic_components.components.base.DynamicComponentContainer
import com.example.components.dynamic_components.components.base.ReviewDefaultBottomDivider

@Composable
fun RadioButtonListReview(
    title: String,
    description: String?,
    value: String
){
    DynamicComponentContainer(
        header = {
            DefaultComponentHeader(
                modifier = Modifier.fillMaxWidth(),
                title = title,
                description = description
            )
        },
        content = {
            Text(
                text = value
            )
        },
        footer = {
            ReviewDefaultBottomDivider()
        }
    )
}