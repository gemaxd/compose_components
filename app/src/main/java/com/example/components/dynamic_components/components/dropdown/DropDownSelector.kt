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
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.components.dynamic_components.components.base.DefaultComponentHeader
import com.example.components.feature.dynamic_form.domain.model.Option

@Composable
fun DropDownSelector(
    title: String,
    description: String?,
    selectedItem: Option = emptyOption(),
    onExpand: (Boolean) -> Unit
) {
    val arrowDropDownPainter = rememberVectorPainter(Icons.Default.ArrowDropDown)
    val textColor: Color = if (selectedItem.optionCode == 0) Color.Gray else Color.Black

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
                    text = selectedItem.optionDescription,
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