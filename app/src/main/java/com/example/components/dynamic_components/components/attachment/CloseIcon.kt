package com.example.components.dynamic_components.components.attachment

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.components.dynamic_components.components.utils.enums.IconSizeEnums

@Composable
@Preview(showBackground = true)
fun CloseIcon(
    modifier: Modifier = Modifier,
    iconSize: IconSizeEnums = IconSizeEnums.MEDIUM_SIZE,
    onClose: () -> Unit = {}
){
    Box(
        modifier = modifier.background(
            color = Color.Black.copy(alpha = 0.5f),
            shape = CircleShape,
        )
    ) {
        Icon(
            imageVector = Icons.Default.Close,
            contentDescription = "Close",
            tint = Color.White,
            modifier = Modifier
                .size(iconSize.size)
                .align(Alignment.TopEnd)
                .clickable { onClose() }
        )
    }
}