package com.example.components.dynamic_components.components.radiobuttonslist

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.components.dynamic_components.components.base.DefaultComponentHeader
import com.example.components.dynamic_components.components.dropdown.emptyOption
import com.example.components.dynamic_components.components.radiobutton.RadioButtonOption
import com.example.components.dynamic_components.components.utils.DEFAULT_OPTION_HEIGHT
import com.example.components.feature.dynamic_form.domain.model.Option

@Composable
fun RadioButtonListContent(
    title: String,
    description: String?,
    items: List<Option>,
    onItemSelected: (Option) -> Unit
){
    val currentHeight = (items.size * DEFAULT_OPTION_HEIGHT).dp
    var selectedOption by remember { mutableStateOf(emptyOption()) }

    LaunchedEffect(Unit){
        selectedOption = items.firstOrNull { it.optionChecked } ?: emptyOption()
    }

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
                    RadioButtonOption(
                        selected = selectedOption == currentOption,
                        option = currentOption,
                        onClick = { option ->
                            selectedOption = option
                            onItemSelected(option)
                        }
                    )
                }
            }
        )
    }
}