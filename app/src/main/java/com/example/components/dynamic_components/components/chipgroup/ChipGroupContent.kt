package com.example.components.dynamic_components.components.chipgroup

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.material.Chip
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.components.dynamic_components.components.singlelinetext.TextFieldContent
import com.example.components.feature.dynamic_form.domain.model.Option

@ExperimentalMaterialApi
@ExperimentalAnimationApi
@ExperimentalLayoutApi
@Composable
fun ChipGroupContent(
    title: String,
    description: String?,
    keyboard: KeyboardType,
    onChipAdd: (Option) -> Unit,
    onChipRemove: (Option) -> Unit
){
    val selectedChips = remember { mutableStateListOf<Option>() }

    Column {
        TextFieldContent(
            title = title,
            description = description,
            keyboard = keyboard,
            onDone = {
                val createdOption = Option(optionDescription = it, optionChecked = true)
                selectedChips.add(createdOption)
                onChipAdd(createdOption)
            }
        )

        FlowRow(
            modifier = Modifier.padding(vertical = 16.dp),
            maxItemsInEachRow = 3,
        ) {
            selectedChips.forEach { chip ->
                Box(modifier = Modifier.padding(horizontal = 4.dp)){
                    Chip(
                        onClick = {
                            onChipRemove(chip)
                            selectedChips.remove(chip)
                        },
                        modifier = Modifier.requiredHeight(32.dp)
                    ) {
                        Text(text = chip.optionDescription)
                    }
                }
            }
        }
    }
}