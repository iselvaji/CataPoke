package com.cw.catapoke.presentation.common

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.cw.catapoke.R
import com.cw.catapoke.utils.Constants


/**
 * Compose util contains the reusable compose functions
 *
 */
object ComposeUtil {

    @Composable
    fun LoadImage(imageUrl : String) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(imageUrl)
                .crossfade(true)
                .error(R.drawable.ic_error)
                .build(),
            placeholder = painterResource(R.drawable.ic_loading),
            contentDescription = stringResource(R.string.content_desc_image_desc),
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(150.dp)
                .clip(CircleShape)
        )
    }

    @Composable
    fun HeaderText(message: String?, tag:String="") {
        message?.let {
            Text(text = it,
                Modifier.padding(10.dp).testTag(tag),
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp
            )
        }
    }

    @Composable
    fun ContentText(message: String?, tag:String="" ) {
        message?.let {
            Text(text = it,
                Modifier.padding(10.dp).testTag(tag),
                fontWeight = FontWeight.Medium,
                fontSize = 16.sp
            )
        }
    }

    @Composable
    fun ContentTextWithColor(message: String?, tag:String="", color: Color) {
        message?.let {
            Text(text = it,
                Modifier.padding(10.dp).testTag(tag),
                color = color,
                fontWeight = FontWeight.Medium,
                fontSize = 16.sp
            )
        }
    }

    @Composable
    fun SubHeaderText(message: String?, tag:String="" ) {
        message?.let {
            Text(
                text = it,
                Modifier.padding(10.dp).testTag(tag),
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )
        }
    }

    @Composable
    fun ErrorText(message: String?) {
        message?.let {
            Text(text = it,
                fontWeight = FontWeight.Medium,
                color = MaterialTheme.colors.error,
                fontSize = 18.sp
            )
        }
    }

    @Composable
    fun LoadingProgress() {
        CircularProgressIndicator(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .wrapContentWidth(Alignment.CenterHorizontally)
                .testTag(Constants.TEST_TAG_PROGRESS))
    }
}