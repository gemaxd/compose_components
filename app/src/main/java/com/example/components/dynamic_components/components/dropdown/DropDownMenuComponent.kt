package com.example.components.dynamic_components.components.dropdown

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.components.dynamic_components.components.structurebasis.DefaultComponentHeader

@Composable
fun DropdownMenuComponent(
    title: String,
    description: String,
    items: List<Pair<Int, String>>,
    selectedItem: Pair<Int, String>,
    onItemSelected: (Pair<Int, String>) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    var localSelectedItem by remember { mutableStateOf(selectedItem) }

    Column {
        DropDownDefaultSelectorHeader(
            title = title,
            description = description,
            selectedItem = localSelectedItem
        ) {
            expanded = it
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.fillMaxWidth()
        ) {
            items.forEachIndexed { index, item ->
                DropdownMenuItem(
                    onClick = {
                        localSelectedItem = item
                        onItemSelected(item)
                        expanded = false
                    },
                    text = {
                        Text(
                            text = item.second,
                            modifier = Modifier.padding(16.dp)
                        )
                    }
                )

                if (index < items.size - 1) {
                    Divider()
                }
            }
        }
    }
}

@Composable
fun DropdownChildMenuComponent(
    title: String,
    description: String,
    items: List<Pair<Int, String>>,
    selectedItem: Pair<Int, String>,
    onItemSelected: (Pair<Int, String>) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    Column {
        DropDownDefaultSelectorHeader(
            title = title,
            description = description,
            selectedItem = selectedItem
        ) {
            expanded = it
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.fillMaxWidth()
        ) {
            items.forEachIndexed { index, item ->
                DropdownMenuItem(
                    onClick = {
                        onItemSelected(item)
                        expanded = false
                    },
                    text = {
                        Text(
                            text = item.second,
                            modifier = Modifier.padding(16.dp)
                        )
                    }
                )

                if (index < items.size - 1) {
                    Divider()
                }
            }
        }
    }
}

@Composable
fun DropDownDefaultSelectorHeader(
    title: String,
    description: String?,
    selectedItem: Pair<Int, String> = emptyOption(),
    onExpand: (Boolean) -> Unit
) {
    val arrowDropDownPainter = rememberVectorPainter(Icons.Default.ArrowDropDown)
    val textColor: Color = if (selectedItem.first == 0) Color.Gray else Color.Black

    Column {
        DefaultComponentHeader(
            modifier = Modifier.fillMaxWidth(),
            title = title,
            description = description
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .border(
                    border = BorderStroke(1.dp, Color.Gray),
                    shape = RoundedCornerShape(5.dp)
                )
        ) {
            Row(
                Modifier
                    .fillMaxWidth()
                    .clickable(
                        onClick = {
                            onExpand(true)
                        }
                    )
                    .padding(16.dp)
            ) {
                Text(
                    text = selectedItem.second,
                    modifier = Modifier.weight(1f),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    color = textColor
                )
                Icon(
                    painter = arrowDropDownPainter,
                    contentDescription = null
                )
            }
        }
    }
}