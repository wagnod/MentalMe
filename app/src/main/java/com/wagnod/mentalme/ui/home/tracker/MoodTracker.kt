package com.wagnod.mentalme.ui.home.tracker

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.wagnod.mentalme.ui.theme.MentalMeTheme


@Composable
fun MoodTracker() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Green)
    ) {

    }
}

@Preview(showBackground = true)
@Composable
fun PreviewTracker() {
    MentalMeTheme() {
        MoodTracker()
    }
}