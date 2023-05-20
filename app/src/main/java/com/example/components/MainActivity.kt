package com.example.components

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.ExperimentalAnimationApi
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

@ExperimentalMaterial3Api
@ExperimentalAnimationApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val galleryLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val data: Intent? = result.data
            val selectedImageUri: Uri? = data?.data
            // Lidar com a imagem selecionada da galeria
        } else {
            // O usuário cancelou a seleção da imagem
        }
    }
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