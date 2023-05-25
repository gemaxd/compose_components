package com.example.components.dynamic_components.components.singlelinetext

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.components.dynamic_components.components.base.DefaultComponentReviewHeader
import com.example.components.dynamic_components.components.base.DynamicComponentContainer
import com.example.components.ui.theme.Typography

@Composable
fun SimpleComponentReview(
    title: String,
    description: String?,
    values: List<String?>
) {
    Box(modifier = Modifier.padding(8.dp)) {
        DynamicComponentContainer(
            header = {
                DefaultComponentReviewHeader(
                    modifier = Modifier.fillMaxWidth(),
                    title = title,
                    description = description
                )
            },
            content = {
                values.forEach { value ->
                    Text(
                        text = value ?: "Valor não capturado",
                        style = Typography.body1,
                    )
                }
            },
            footer = {
                Spacer(modifier = Modifier.padding(8.dp))
            }
        )
    }
}