package com.example.components.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.components.feature.dynamic_form.presentation.dynamic_form.DynamicFormScreen
import com.example.components.feature.dynamic_form.presentation.dynamic_form.DynamicFormState
import com.example.components.feature.dynamic_form.presentation.dynamic_form.DynamicFormViewModel
import com.example.components.feature.dynamic_form.presentation.dynamic_preview.DynamicReviewScreen
import com.example.components.feature.dynamic_form.presentation.dynamic_preview.DynamicReviewState
import com.example.components.feature.dynamic_form.presentation.dynamic_preview.DynamicReviewViewModel

@ExperimentalMaterial3Api
@ExperimentalLayoutApi
@ExperimentalMaterialApi
@ExperimentalAnimationApi
@ExperimentalFoundationApi
@Composable
fun SetupNavigation(){
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.DynamicFormScreen.route
    ){
        composable(
            route = Screen.DynamicFormScreen.route
        ){
            val viewModel = hiltViewModel<DynamicFormViewModel>()
            val state = viewModel.state.collectAsState(initial = DynamicFormState())

            DynamicFormScreen(
                navHostController = navController,
                state = state.value,
                onEvent = viewModel::onEvent,
                onComponentEvent = viewModel::onComponentEvent
            )
        }

        composable(
            route = Screen.DynamicFormReviewScreen.route
        ){
            val viewModel = hiltViewModel<DynamicReviewViewModel>()
            val state = viewModel.state.collectAsState(initial = DynamicReviewState())

            DynamicReviewScreen(
                navController = navController,
                state = state.value,
                onEvent = viewModel::onEvent
            )
        }
    }
}