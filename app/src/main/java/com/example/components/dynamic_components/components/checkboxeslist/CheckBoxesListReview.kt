package com.example.components.dynamic_components.components.checkboxeslist

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.components.dynamic_components.components.base.DefaultComponentHeader
import com.example.components.dynamic_components.components.base.DynamicComponentContainer
import com.example.components.dynamic_components.components.base.ReviewDefaultBottomDivider

@Composable
fun CheckBoxesListReview(
    title: String,
    description: String?,
    items: List<String>
) {
    DynamicComponentContainer(
        header = {
            DefaultComponentHeader(title = title, description = description)
        },
        content = {
            items.forEach {
                Row(
                    modifier = Modifier.padding(4.dp)
                ) {
                    Icon(Icons.Filled.ArrowForward, contentDescription = null)
                    Text(text = it)
                }
            }
        },
        footer = {
            ReviewDefaultBottomDivider()
        }
    )

}