package com.example.components.feature.dynamic_form

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.components.dynamic_components.components.utils.EnumComponentType
import com.example.components.feature.dynamic_form.wrapper.CreateComponents
import com.example.components.feature.model.Component

@ExperimentalAnimationApi
@Composable
fun DynamicFormScreen(){
    val componentStates = remember {
        createComponentsList().map {
            it to mutableStateOf(false)
        }
    }
    val componentValidation = componentStates.all { it.second.value }

    Box(modifier = Modifier.verticalScroll(rememberScrollState())) {
        Column(
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 16.dp)
        ) {
            CreateComponents(
                components = createComponentsList(),
                onComponentValidationChange = { component, isValid ->
                    componentStates.find { it.first == component }?.second?.value = isValid
                }
            )
            Button(
                onClick = {},
                enabled = componentValidation
            ) {
                Text(text = "Click ME!")
            }
        }
    }
}

fun createComponentsList() = listOf(
    Component(
        componentType = EnumComponentType.MULTILINE_TEXT_FIELD,
        componentLabel = "MultiLine",
        componentMaxLength = 150
    ),
    Component(
        componentType = EnumComponentType.MULTILINE_TEXT_FIELD,
        componentLabel = "MultiLine 02",
        componentMaxLength = 300
    )
)