package com.example.components.dynamic_components.components.chipgroup

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Chip
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.components.dynamic_components.components.attachment.CloseIcon
import com.example.components.dynamic_components.components.utils.enums.IconSizeEnums

@ExperimentalMaterialApi
@Composable
@Preview(showBackground = true)
fun ChipContent(
    modifier: Modifier = Modifier,
    description: String = "teste teste Option",
    onRemoveChip: () -> Unit = {}
){
    Chip(
        onClick = {}
    ) {
        Row {
            Text(
                modifier = modifier.align(alignment = CenterVertically)
                    .padding(end = 8.dp),
                text = description
            )
            CloseIcon(
                modifier = Modifier.align(CenterVertically),
                iconSize = IconSizeEnums.SMALL_SIZE,
                onClose = {
                    onRemoveChip()
                }
            )
        }
    }
}