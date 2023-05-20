package com.example.components.feature.dynamic_form.presentation.dynamic_form

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.components.dynamic_components.components.DynamicComponentEvent
import com.example.components.dynamic_components.components.dropdown.DropdownChildMenuComponent
import com.example.components.dynamic_components.components.dropdown.DropdownMenuComponent
import com.example.components.dynamic_components.components.dropdown.emptyCategory
import com.example.components.dynamic_components.components.dropdown.emptySubCategory
import com.example.components.feature.dynamic_form.presentation.dynamic_form.wrapper.CreateComponents

@ExperimentalMaterial3Api
@ExperimentalAnimationApi
@Composable
fun DynamicFormScreen(
    state: DynamicFormState,
    onEvent: (DynamicFormEvent) -> Unit,
    onComponentEvent: (DynamicComponentEvent) -> Unit,
    onConfirm: () -> Unit
){
    Scaffold(
        topBar = {
            TopBar()
        }){ innerPadding ->
        Box(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            DynamicFormContent(
                state = state,
                onEvent = onEvent,
                onComponentEvent = onComponentEvent,
                onConfirm = onConfirm
            )
        }
    }
}

@ExperimentalMaterial3Api
@Composable
fun TopBar(){
    TopAppBar(
        title = {
            Text(
                text = "Dynamic Forms",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
            )
        },
        navigationIcon = {
            IconButton(onClick = { /* doSomething() */ }) {
                Icon(
                    imageVector = Icons.Filled.Menu,
                    contentDescription = "Localized description"
                )
            }
        },
        actions = {
            IconButton(onClick = { /* doSomething() */ }) {
                Icon(
                    imageVector = Icons.Filled.Favorite,
                    contentDescription = "Localized description"
                )
            }
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = Color.Red,
            titleContentColor = Color.White,
            actionIconContentColor = Color.White,
            navigationIconContentColor = Color.White
        )
    )
}

@Composable
fun DynamicFormContent(
    state: DynamicFormState,
    onEvent: (DynamicFormEvent) -> Unit,
    onComponentEvent: (DynamicComponentEvent) -> Unit,
    onConfirm: () -> Unit
){
    val focusManager = LocalFocusManager.current
    var selectedSubCategory by remember { mutableStateOf(state.subcategories.firstOrNull() ?: emptySubCategory()) }

    Box(
        modifier = Modifier.verticalScroll(rememberScrollState())
    ) {
        Column(
            modifier = Modifier.padding(
                horizontal = 16.dp,
                vertical = 16.dp
            )
        ) {
            DropdownMenuComponent(
                items = state.categories,
                selectedItem = emptyCategory(),
                onItemSelected = {
                    selectedSubCategory = emptySubCategory()
                    onEvent(
                        DynamicFormEvent.LoadSubCategoriesList(it.first)
                    )
                    focusManager.clearFocus(true)
                },
                title = "Categoria",
                description = "Use o campo abaixo para selecionar a categoria que irá carregar as sub-categorias."
            )

            Spacer(modifier = Modifier.padding(8.dp))

            DropdownChildMenuComponent(
                items = state.subcategories,
                selectedItem = selectedSubCategory,
                onItemSelected = {
                    selectedSubCategory = it
                    onEvent(
                        DynamicFormEvent.LoadComponentList(it.first)
                    )
                    focusManager.clearFocus(true)
                },
                title = "Sub-Categoria",
                description = "Use o campo abaixo para selecionar a sub-categoria que irá carregar os components."
            )

            Spacer(modifier = Modifier.padding(16.dp))

            if(state.isLoading){
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    CircularProgressIndicator(
                        modifier = Modifier.padding(20.dp)
                    )
                }
            } else {
                CreateComponents(
                    components = state.components,
                    onComponentEvent = onComponentEvent
                )

                if(state.components.isNotEmpty()){
                    Button(
                        onClick = { onConfirm() },
                        enabled = state.isValid
                    ) {
                        Text(text = "Click ME!")
                    }
                }
            }
        }
    }
}