package com.wagnod.core_ui.sheet.data

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class BottomSheetParams(
    val topPadding: Dp = 0.dp,
    val content: @Composable () -> Unit
)