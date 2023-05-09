package com.example.components.feature.dynamic_form.presentation.dynamic_form

import android.widget.Toast
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.components.feature.dynamic_form.presentation.dynamic_form.wrapper.CreateComponents

@ExperimentalAnimationApi
@Composable
fun DynamicFormScreen(
    viewModel: DynamicFormViewModel = hiltViewModel()
){
    val state by viewModel.state.collectAsState(DynamicFormState())
    val ctx = LocalContext.current

    Box(modifier = Modifier.verticalScroll(rememberScrollState())) {
        Column(
            modifier = Modifier.padding(
                horizontal = 16.dp,
                vertical = 16.dp
            )
        ) {
            CreateComponents(
                components = state.components,
                onChangeValidation = { component, isValid ->
                    viewModel.onEvent(
                        DynamicFormEvent.UpdateValidations(
                            component = component,
                            isValid = isValid
                        )
                    )
                },
                onValueChange = { component, newValue ->
                    viewModel.onEvent(
                        DynamicFormEvent.UpdateValues(
                            component = component,
                            value = newValue
                        )
                    )
                }
            )
            Button(
                onClick = {
                    Toast.makeText(ctx, viewModel.prepareContentMessage(), Toast.LENGTH_LONG).show()
                },
                enabled = state.isValid
            ) {
                Text(text = "Click ME!")
            }
        }
    }
}