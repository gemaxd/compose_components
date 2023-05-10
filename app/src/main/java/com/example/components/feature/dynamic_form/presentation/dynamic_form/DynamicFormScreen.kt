package com.example.components.feature.dynamic_form.presentation.dynamic_form

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.components.dynamic_components.components.dropdown.DropdownMenuComponent
import com.example.components.feature.dynamic_form.presentation.dynamic_form.wrapper.createComponents

@ExperimentalAnimationApi
@Composable
fun DynamicFormScreen(
    viewModel: DynamicFormViewModel = hiltViewModel()
){
    val focusManager = LocalFocusManager.current

    val state by viewModel.state.collectAsState(DynamicFormState())
    //var selectedItem by remember { mutableStateOf(state.categories[0]) }

    Box(modifier = Modifier.verticalScroll(rememberScrollState())) {
        Column(
            modifier = Modifier.padding(
                horizontal = 16.dp,
                vertical = 16.dp
            )
        ) {

            DropdownMenuComponent(
                items = state.categories,
                selectedItem = state.categories.let {
                    if(it.isNotEmpty()) it.first() else Pair(0, "Item 1")
                },
                onItemSelected = {
                    //selectedItem = it
                    viewModel.loadComponentsList(categoryId = it.first)
                    focusManager.clearFocus(true)
                }
            )
            if(state.isLoading){
                CircularProgressIndicator()
            }else{
                createComponents(
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
                        print(viewModel.prepareContentMessage())
                    },
                    enabled = state.isValid
                ) {
                    Text(text = "Click ME!")
                }
            }
        }
    }
}