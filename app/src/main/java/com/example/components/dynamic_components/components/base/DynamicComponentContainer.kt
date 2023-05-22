package com.example.components.dynamic_components.components.base

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable

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