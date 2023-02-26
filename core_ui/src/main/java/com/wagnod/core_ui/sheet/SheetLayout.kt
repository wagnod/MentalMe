package com.wagnod.core_ui.sheet

import android.os.Handler
import android.os.Looper
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.skydoves.landscapist.coil.CoilImage
import com.wagnod.core_ui.sheet.data.BottomSheetItem
import com.wagnod.core_ui.sheet.data.SheetItem
import com.wagnod.core_ui.theme.calloutRegular
import com.wagnod.core_ui.theme.graphicsPrimary
import com.wagnod.core_ui.theme.textPrimary


@Composable
fun SheetLayout(
    currentSheet: List<Pair<BottomSheetItem, () -> Unit>>,
//    closeCallback: () -> Unit
) {
    LazyColumn {
        item {
            TopHideButton()
        }
        items(currentSheet.size) { position ->
            SheetLayoutItem(currentSheet[position].first) {
//                closeCallback.invoke()
                Handler(Looper.getMainLooper()).postDelayed({
                    currentSheet[position].second.invoke()
                }, 150)
            }
        }
        item {
            Spacer(modifier = Modifier.height(4.dp))
        }
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
private fun SheetLayoutItem(
    item: BottomSheetItem,
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
        val (title, icon) = when(item) {
            is SheetItem -> item.title to item.icon
            else -> throw IllegalStateException("Wrong BottomSheetItemType")
        }
        CoilImage(
            modifier = Modifier
                .clip(CircleShape)
                .size(24.dp),
            imageModel = { icon },
        )
        Spacer(modifier = Modifier.width(20.dp))
        Text(
            text = stringResource(title),
            style = MaterialTheme.typography.calloutRegular,
            color = MaterialTheme.colors.textPrimary
        )
    }
}
