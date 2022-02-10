package com.example.components.presentation.components.text_field

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@ExperimentalAnimationApi
@Composable
fun CustomTextFieldComponent(maxLength: Int) {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.End

    ) {
        CXCMultilineTextField(maxLength = maxLength)
    }
}

@Composable
fun CXCMultilineTextField(maxLength: Int) {
    var textValue by remember { mutableStateOf("")}

    MultilineTextField(
        modifier = Modifier.fillMaxWidth(),
        value = textValue,
        onValueChange = { textValue = it},
        maxLines = 3,
        minLines = 3
    )

    Row(
        modifier = Modifier
            .fillMaxWidth()
    ){
        Box(modifier = Modifier.weight(8f)) {
            if(maxLength < textValue.length) {
                Text(
                    modifier = Modifier
                        .padding(5.dp),
                    text = "Limite atingido! Reveja a mensagem",
                    color = Color.Red,
                    textAlign = TextAlign.Left
                )
            }
        }

        Text(
            modifier = Modifier
                .weight(2f)
                .padding(5.dp),
            text = "${textValue.length} / $maxLength",
            color = if (maxLength < textValue.length) Color.Red else Color.Black,
            textAlign = TextAlign.Right
        )
    }
}

@Composable
fun MultilineTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    textStyle: TextStyle = LocalTextStyle.current,
    label: @Composable (() -> Unit)? = null,
    placeholder: @Composable (() -> Unit)? = null,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    isError: Boolean = false,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    singleLine: Boolean = false,
    minLines: Int,
    maxLines: Int = Int.MAX_VALUE,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    shape: Shape = MaterialTheme.shapes.small,
    colors: TextFieldColors = TextFieldDefaults.outlinedTextFieldColors()
) {
    val heightState = remember { mutableStateOf<Int?>(null) }
    var heightUpdateNeeded by remember(modifier, textStyle) { mutableStateOf(true) }
    val height = with(LocalDensity.current) {
        heightState.value?.toDp()
    } // to use if nullable unwrapping
    Box(
        modifier
            .height(IntrinsicSize.Min)
            .width(IntrinsicSize.Min)
    ) {
        if (heightUpdateNeeded) {
            OutlinedTextField(
                value = value + "\n".repeat(minLines),
                onValueChange = onValueChange,
                enabled = enabled,
                readOnly = readOnly,
                textStyle = textStyle,
                label = label,
                placeholder = placeholder,
                leadingIcon = leadingIcon,
                trailingIcon = trailingIcon,
                isError = isError,
                visualTransformation = visualTransformation,
                keyboardOptions = keyboardOptions,
                keyboardActions = keyboardActions,
                singleLine = singleLine,
                maxLines = maxLines,
                interactionSource = interactionSource,
                shape = shape,
                colors = colors,
                modifier = Modifier
                    .fillMaxSize()
                    .alpha(0f)
                    .onSizeChanged {
                        heightUpdateNeeded = false
                        println("onSizeChanged $it")
                        heightState.value = it.height
                    }
            )
        }
        if (height != null) {
            OutlinedTextField(
                value = value,
                onValueChange = onValueChange,
                enabled = enabled,
                readOnly = readOnly,
                textStyle = textStyle,
                label = label,
                placeholder = placeholder,
                leadingIcon = leadingIcon,
                trailingIcon = trailingIcon,
                isError = isError,
                visualTransformation = visualTransformation,
                keyboardOptions = keyboardOptions,
                keyboardActions = keyboardActions,
                singleLine = singleLine,
                maxLines = maxLines,
                interactionSource = interactionSource,
                shape = shape,
                colors = colors,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(height)
            )
        }
    }
}
