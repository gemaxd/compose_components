package com.example.components.dynamic_components.components.dropdown

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.components.feature.dynamic_form.domain.model.Option

@Composable
fun DropdownChildContent(
    title: String,
    description: String,
    items: List<Option>,
    selectedItem: Option,
    onItemSelected: (Option) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    var localSelectedItem = selectedItem

    LaunchedEffect(Unit){
        localSelectedItem = items.firstOrNull { it.optionChecked } ?: emptyOption()
    }

    Column {
        DropDownSelector(
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
                        onItemSelected(item)
                        expanded = false
                    },
                    text = {
                        Text(
                            text = item.optionDescription,
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