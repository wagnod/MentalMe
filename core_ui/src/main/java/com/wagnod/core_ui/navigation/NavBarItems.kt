package com.wagnod.core_ui.navigation

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.skydoves.landscapist.coil.CoilImage
import com.wagnod.core_ui.theme.*

@Composable
fun NavBarTitle(title: String, @DrawableRes imageId: Int? = null) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        if (imageId != null) {
            Image(
                painter = painterResource(imageId),
                contentDescription = "",
                modifier = Modifier
                    .width(18.dp)
                    .height(18.dp)
                    .clip(RoundedCornerShape(4.dp)), contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.width(8.dp))
        }
        Text(text = title, style = MaterialTheme.typography.title2, color = MaterialTheme.colors.textPrimary)
    }
}

@Composable
fun BackButton(onBack: () -> Unit) {
    Icon(
        painter = rememberVectorPainter(image = Icons.Default.ArrowBack),
        contentDescription = "",
        modifier = Modifier
            .padding(start = 12.dp)
            .clip(RoundedCornerShape(14.dp))
            .clickable { onBack() }
            .padding(4.dp)
    )
}