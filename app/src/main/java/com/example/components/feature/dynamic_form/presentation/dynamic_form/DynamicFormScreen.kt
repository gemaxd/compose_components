package com.example.components.feature.dynamic_form.presentation.dynamic_form

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.components.dynamic_components.components.DynamicComponentEvent
import com.example.components.dynamic_components.components.dropdown.DropdownChildContent
import com.example.components.dynamic_components.components.dropdown.DropdownContent
import com.example.components.feature.dynamic_form.presentation.dynamic_form.wrapper.CreateFormComponents
import com.example.components.navigation.Screen

@ExperimentalLayoutApi
@ExperimentalMaterial3Api
@ExperimentalAnimationApi
@Composable
fun DynamicFormScreen(
    navHostController: NavHostController? = null,
    state: DynamicFormState,
    onEvent: (DynamicFormEvent) -> Unit,
    onComponentEvent: (DynamicComponentEvent) -> Unit
){
    Scaffold(
        topBar = {
            DynamicFormTopBar()
        }){ innerPadding ->
        Box(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            DynamicFormContent(
                navController = navHostController,
                state = state,
                onEvent = onEvent,
                onComponentEvent = onComponentEvent
            )
        }
    }
}

@ExperimentalMaterial3Api
@Composable
fun DynamicFormTopBar(){
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

@ExperimentalLayoutApi
@Composable
fun DynamicFormContent(
    navController: NavHostController? = null,
    state: DynamicFormState,
    onEvent: (DynamicFormEvent) -> Unit,
    onComponentEvent: (DynamicComponentEvent) -> Unit
){
    val focusManager = LocalFocusManager.current
    val selectedCategory = state.selectedCategory
    val selectedSubCategory= state.selectedSubCategory

    Box(
        modifier = Modifier.verticalScroll(rememberScrollState())
    ) {
        Column(
            modifier = Modifier.padding(
                horizontal = 16.dp,
                vertical = 16.dp
            )
        ) {
            DropdownContent(
                items = state.categories,
                selectedItem = selectedCategory,
                onItemSelected = {
                    onEvent(DynamicFormEvent.OnCategorySelection(it))
                    onEvent(DynamicFormEvent.LoadSubCategoriesList(it.optionCode))
                    focusManager.clearFocus(true)
                },
                title = "Categoria",
                description = "Use o campo abaixo para selecionar a categoria que irá carregar as sub-categorias."
            )

            Spacer(modifier = Modifier.padding(8.dp))

            DropdownChildContent(
                items = state.subcategories,
                selectedItem = selectedSubCategory,
                onItemSelected = {
                    onEvent(DynamicFormEvent.OnSubCategorySelection(it))
                    onEvent(DynamicFormEvent.LoadComponentList(it.optionCode))
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
                CreateFormComponents(
                    components = state.components,
                    onComponentEvent = onComponentEvent
                )

                if(state.components.isNotEmpty()){
                    Button(
                        onClick = {
                            navController?.navigate(
                                Screen.DynamicFormReviewScreen.route
                            ){
                                popUpTo(Screen.DynamicFormScreen.route)
                            }
                        },
                        enabled = state.isValid
                    ) {
                        Text(text = "Click ME!")
                    }
                }
            }
        }
    }
}