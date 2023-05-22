package com.example.components

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.material.Scaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.components.feature.dynamic_form.presentation.dynamic_form.DynamicFormScreen
import com.example.components.feature.dynamic_form.presentation.dynamic_form.DynamicFormState
import com.example.components.feature.dynamic_form.presentation.dynamic_form.DynamicFormViewModel
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.android.AndroidEntryPoint

@ExperimentalLayoutApi
@ExperimentalMaterial3Api
@ExperimentalAnimationApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Scaffold(
                content = {
                    it.calculateTopPadding()

                    val viewModel = hiltViewModel<DynamicFormViewModel>()
                    val state = viewModel.state.collectAsState(initial = DynamicFormState())
                    val ctx = LocalContext.current

                    val systemUiController = rememberSystemUiController()
                    systemUiController.setSystemBarsColor(color = Color.Red)

                    DynamicFormScreen(
                        state = state.value,
                        onEvent = viewModel::onEvent,
                        onComponentEvent = viewModel::onComponentEvent
                    ){
                        Toast.makeText(ctx, viewModel.prepareContentMessage(), Toast.LENGTH_SHORT).show()
                    }
                }
            )
        }
    }
}