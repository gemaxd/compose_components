package com.example.components.navigation

sealed class Screen(val route: String){
    object DynamicFormScreen: Screen("dynamic_screen")
    object DynamicFormReviewScreen: Screen("dynamic_Review_screen")
    object DynamicFormHistoryScreen: Screen("dynamic_history_screen")

    fun withArgs(vararg args: Any) : String{
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }
}