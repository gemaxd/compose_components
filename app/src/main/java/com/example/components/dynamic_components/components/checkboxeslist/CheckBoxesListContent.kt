package com.example.components.dynamic_components.components.checkboxeslist

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.components.dynamic_components.components.base.DefaultComponentHeader
import com.example.components.dynamic_components.components.checkbox.CheckBoxOption
import com.example.components.dynamic_components.components.utils.DEFAULT_OPTION_HEIGHT
import com.example.components.feature.dynamic_form.domain.model.Option

@Composable
fun CheckBoxesListContent(
    title: String,
    description: String?,
    items: List<Option>,
    onItemSelected: (Option) -> Unit
){
    val currentHeight = (items.size * DEFAULT_OPTION_HEIGHT).dp

    Column {
        DefaultComponentHeader(
            modifier = Modifier.fillMaxWidth(),
            title = title,
            description = description
        )

        LazyColumn(
            modifier = Modifier.heightIn(
                max = currentHeight,
                min = currentHeight
            ),
            content = {
                this.items(items.size){
                    val currentOption = items[it]
                    CheckBoxOption(
                        option = currentOption,
                        onCheckStateChange = { option ->
                            onItemSelected(option)
                        }
                    )
                }
            }
        )
    }
}