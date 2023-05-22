package com.example.components.dynamic_components.components.checkbox

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Checkbox
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.components.feature.dynamic_form.domain.model.Option

@Composable
@Preview(showBackground = true)
fun CheckBoxOption(
    option: Option = Option(
        optionDescription = "Descrição"
    ),
    onCheckStateChange: (Option) -> Unit = {}
){
    var isChecked by remember { mutableStateOf(false) }

    Row(modifier = Modifier.fillMaxWidth()) {
        Text(
            modifier = Modifier
                .weight(9f)
                .align(alignment = CenterVertically)
                .padding(horizontal = 8.dp),
            text = option.optionDescription,
        )
        Checkbox(
            modifier = Modifier.weight(1f),
            checked = isChecked,
            onCheckedChange = {
                isChecked = !isChecked
                onCheckStateChange(option)
            }
        )
    }
}