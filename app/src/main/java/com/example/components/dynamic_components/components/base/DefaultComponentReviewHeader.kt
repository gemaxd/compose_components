package com.example.components.dynamic_components.components.base

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.components.ui.theme.Typography

@Composable
fun DefaultComponentReviewHeader(
    modifier: Modifier = Modifier,
    title: String,
    description: String?,
){
    DefaultComponentReviewHeaderContent(
        modifier = modifier,
        title = title,
        description = description
    )
}

@Composable
fun DefaultComponentReviewHeaderContent(
    modifier: Modifier = Modifier,
    title: String,
    description: String?,

){
    Column(modifier = modifier) {
        Text(
            text = title,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            style = Typography.body2,
        )

        description?.let { description ->
            if(description.isNotEmpty()){
                Text(
                    text = description,
                    fontSize = 12.sp,
                    color = Color.Gray,
                    style = Typography.subtitle1,
                )
            }
        }
    }

    Spacer(modifier = Modifier.padding(4.dp))
}