package com.example.components.dynamic_components.components.dropdown

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.unit.dp

@Composable
fun DropdownMenuComponent(
    items: List<Pair<Int, String>>,
    selectedItem: Pair<Int, String>,
    onItemSelected: (Pair<Int, String>) -> Unit
) {
    val arrowDropDownPainter = rememberVectorPainter(Icons.Default.ArrowDropDown)
    var expanded by remember { mutableStateOf(false) }
    val selectedIndex = items.indexOf(selectedItem)

    Column {
        Row(
            Modifier
                .fillMaxWidth()
                .clickable(onClick = { expanded = true })
                .padding(16.dp)
        ) {
            Text(
                text = selectedItem.second,
                modifier = Modifier.weight(1f)
            )
            Icon(
                painter = arrowDropDownPainter,
                contentDescription = null
            )
        }
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.fillMaxWidth()
        ) {
            items.forEachIndexed { index, item ->
                DropdownMenuItem(onClick = {
                    onItemSelected(item)
                    expanded = false
                }) {
                    Text(
                        text = item.second,
                        modifier = Modifier.padding(16.dp)
                    )
                }
                if (index < items.size - 1) {
                    Divider()
                }
            }
        }
    }
}