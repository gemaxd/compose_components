package com.example.components.dynamic_components.components.attachment

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.components.dynamic_components.components.base.DefaultComponentHeader
import com.example.components.dynamic_components.components.base.DynamicComponentContainer
import com.example.components.dynamic_components.components.base.ReviewDefaultBottomDivider
import com.example.components.feature.dynamic_form.domain.model.Attachment

@Composable
fun AttachmentFieldReview(
    title: String,
    description: String?,
    items: List<Attachment>
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
                    ImageFromUri(it)
                    Text(text = it.uri?.path ?: "")
                }
            }
        },
        footer = {
            ReviewDefaultBottomDivider()
        }
    )
}