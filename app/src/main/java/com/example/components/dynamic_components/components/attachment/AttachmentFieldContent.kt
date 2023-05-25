package com.example.components.dynamic_components.components.attachment

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.components.dynamic_components.components.multilinetext.MultilineTextFieldContent
import com.example.components.feature.dynamic_form.domain.model.Attachment
import com.example.components.feature.dynamic_form.domain.model.Component

@ExperimentalAnimationApi
@Composable
fun AttachmentFieldContent(
    text: String,
    component: Component,
    onTextChange: (String) -> Unit,
    onAttachmentAdd: (Attachment) -> Unit,
    onAttachmentRemove: (Attachment) -> Unit
) {
    val attachments = remember { mutableStateListOf<Attachment>() }
    val configuration = LocalConfiguration.current
    val screenWidthDp = configuration.screenWidthDp

    LaunchedEffect(Unit){
        attachments.addAll(component.componentAttachments)
    }

    val galleryLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val data: Intent? = result.data
            val selectedImageUri: Uri? = data?.data
            val attachment = Attachment(selectedImageUri)

            onAttachmentAdd(attachment)
            attachments.add(attachment)
        }
    }

    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        MultilineTextFieldContent(text = text, component = component, onChange = onTextChange)

        val componentWidth = screenWidthDp / 3
        val componentHeight = screenWidthDp / 7

        Box {
            LazyVerticalGrid(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .heightIn(max = (((componentHeight + 8) * 2)).dp),
                columns = GridCells.Fixed(3),
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                items(attachments.size) { item ->
                    Box(
                        modifier = Modifier
                            .padding(4.dp)
                            .width(componentWidth.dp)
                            .height(componentHeight.dp)
                            .border(
                                BorderStroke(1.dp, color = Color.LightGray),
                                RoundedCornerShape(5.dp)
                            ),
                    ) {
                        ImageFromUri(attachments[item])
                        CloseIcon(
                            modifier = Modifier
                                .align(Alignment.TopEnd)
                                .padding(4.dp)
                        ){
                            onAttachmentRemove(attachments[item])
                            attachments.removeAt(item)
                        }

                    }
                }
            }
        }

        Spacer(modifier = Modifier.padding(4.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            Button(onClick = {
                galleryLauncher.launch(createGalleryIntent())
            }) {
                Text(text = "ANEXAR")
            }
        }
    }
}

@Composable
fun ImageFromUri(attachment: Attachment) {
    val painter = rememberAsyncImagePainter(attachment.uri)

    Image(
        painter = painter,
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .size(200.dp)
    )
}

fun createGalleryIntent(): Intent {
    val intent = Intent(Intent.ACTION_PICK)
    intent.type = "image/*"
    return intent
}