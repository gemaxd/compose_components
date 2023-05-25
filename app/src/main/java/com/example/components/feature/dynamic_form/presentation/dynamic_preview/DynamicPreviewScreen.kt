package com.example.components.feature.dynamic_form.presentation.dynamic_preview

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.components.feature.dynamic_form.presentation.dynamic_preview.wrapper.CreateReviewComponents

@ExperimentalMaterial3Api
@ExperimentalAnimationApi
@ExperimentalLayoutApi
@Composable
fun DynamicReviewScreen(
    navController: NavHostController,
    state: DynamicReviewState,
    onEvent: (DynamicReviewEvent) -> Unit,
){
    Scaffold(
        topBar = {
            DynamicReviewTopBar(
                navController = navController
            )
        }){ innerPadding ->
        Box(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            DynamicReviewContent(
                navController = navController,
                state = state,
                onEvent = onEvent
            )
        }
    }
}

@ExperimentalMaterial3Api
@Composable
fun DynamicReviewTopBar(
    navController: NavHostController
){
    TopAppBar(
        title = {
            Text(
                text = "Dynamic Review",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
            )
        },
        navigationIcon = {
            IconButton(
                onClick = {
                    navController.popBackStack()
                }
            ) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
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
@ExperimentalAnimationApi
@Composable
fun DynamicReviewContent(
    navController: NavHostController,
    state: DynamicReviewState,
    onEvent: (DynamicReviewEvent) -> Unit,
){
    Box(
        modifier = Modifier.verticalScroll(rememberScrollState())
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            CreateReviewComponents(
                components = state.components
            )
        }
    }
}