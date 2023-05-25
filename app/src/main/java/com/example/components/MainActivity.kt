package com.example.components

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.ui.graphics.Color
import com.example.components.navigation.SetupNavigation
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.android.AndroidEntryPoint

@ExperimentalFoundationApi
@ExperimentalMaterialApi
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

                    val systemUiController = rememberSystemUiController()
                    systemUiController.setSystemBarsColor(color = Color.Red)

                    SetupNavigation()
                }
            )
        }
    }
}