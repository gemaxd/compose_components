package com.example.components.dynamic_components.components.radiobutton

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.RadioButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.components.feature.dynamic_form.domain.model.Option

@Composable
@Preview(showBackground = true)
fun RadioButtonOption(
    selected: Boolean = false,
    option: Option = Option(
        optionDescription = "Descrição"
    ),
    onClick: (Option) -> Unit = {}
){
    Row(modifier = Modifier.fillMaxWidth()) {
        Text(
            modifier = Modifier
                .weight(9f)
                .align(alignment = Alignment.CenterVertically)
                .padding(horizontal = 8.dp),
            text = option.optionDescription,
        )
        RadioButton(
            modifier = Modifier.weight(1f),
            selected = selected,
            onClick = {
                onClick(option)
            }
        )
    }
}