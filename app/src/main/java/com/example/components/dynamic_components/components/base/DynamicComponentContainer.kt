package com.example.components.dynamic_components.components.base

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun DynamicComponentContainer(
    header: @Composable (() -> Unit)? = null,
    content: @Composable (() -> Unit)? = null,
    footer: @Composable (() -> Unit)? = null,
){
    Column {
        header?.invoke()
        content?.invoke()
        footer?.invoke()
    }
}