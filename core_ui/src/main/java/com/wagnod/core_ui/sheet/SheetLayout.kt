package com.wagnod.core_ui.sheet

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.skydoves.landscapist.coil.CoilImage
import com.wagnod.core_ui.R
import com.wagnod.core_ui.sheet.data.BottomSheetParams
import com.wagnod.core_ui.theme.*

@Composable
fun SheetLayout(
    currentSheet: BottomSheetParams,
) {
    val maxHeight = LocalConfiguration.current.screenHeightDp.dp - currentSheet.topPadding
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(max = maxHeight)
    ) {
        TopHideButton()
        currentSheet.content()
        Spacer(modifier = Modifier.height(4.dp))
    }
}

@Composable
private fun TopHideButton() = Row(
    modifier = Modifier
        .fillMaxWidth()
        .height(12.dp),
    verticalAlignment = Alignment.CenterVertically,
    horizontalArrangement = Arrangement.Center
) {
    Box(
        modifier = Modifier
            .width(36.dp)
            .height(4.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(MaterialTheme.colors.graphicsPrimary)
    )
}

@Composable
fun UserInfoBottomSheet(
    name: String,
    image: String
) = Column(
    modifier = Modifier
        .fillMaxWidth()
        .padding(top = 16.dp, bottom = 12.dp),
    verticalArrangement = Arrangement.Center,
    horizontalAlignment = CenterHorizontally
) {
    CoilImage(
        imageModel = { image },
        modifier = Modifier
            .size(100.dp)
            .clip(RoundedCornerShape(8.dp))
    )
    Spacer(modifier = Modifier.height(4.dp))
    Text(
        text = name,
        style = MaterialTheme.typography.title1Bold,
        color = MaterialTheme.colors.textPrimary
    )
}


@Composable
fun SheetButtonItem(
    title: String,
    icon: Int,
    onItemClick: () -> Unit
) = Box(
    modifier = Modifier.clickable { onItemClick() }
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp, horizontal = 16.dp)
    ) {
        CoilImage(
            modifier = Modifier
                .clip(RoundedCornerShape(4.dp))
                .size(24.dp),
            imageModel = { icon },
        )
        Spacer(modifier = Modifier.width(20.dp))
        Text(
            text = title,
            style = MaterialTheme.typography.calloutMedium,
            color = MaterialTheme.colors.textPrimary
        )
        Spacer(modifier = Modifier.weight(1f))
        Icon(
            painter = painterResource(id = R.drawable.ic_arrow_right),
            contentDescription = ""
        )
    }
}

@Composable
fun BottomSheetHeader(
    header: String
) = Column(
    modifier = Modifier
        .fillMaxWidth()
        .padding(vertical = 4.dp, horizontal = 16.dp)
) {
    Text(
        text = header,
        style = MaterialTheme.typography.footnoteRegular,
        color = MaterialTheme.colors.textColorSecondary
    )
}