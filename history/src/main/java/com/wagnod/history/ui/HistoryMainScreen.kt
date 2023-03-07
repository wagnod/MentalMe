package com.wagnod.history.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.wagnod.core_ui.navigation.*
import com.wagnod.core_ui.navigators.main.Navigator
import com.wagnod.core_ui.sheet.BottomSheetContent
import com.wagnod.core_ui.sheet.data.BottomSheetParams
import com.wagnod.core_ui.theme.MentalMeTheme
import com.wagnod.core_ui.R
import com.wagnod.history.ui.HistoryContract.*
import org.koin.androidx.compose.getViewModel

@Composable
fun HistoryMainScreen(
    navigator: Navigator,
    viewModel: HistoryViewModel = getViewModel()
) {
    val state = viewModel.viewState.value

    val params = BottomSheetParams(
        content = BottomSheetContent(
            name = state.user.name,
            image = state.user.userImage,
            onGenClick = { },
            onFeedbackClick = { }
        )
    )

    val listener = object : Listener {
        override fun onProfileClick() {
            viewModel.setEvent(Event.ShowBottomSheet(params))
        }
    }

    LaunchedEffect(Unit) {
        viewModel.setEvent(Event.Init)
    }

    HistoryScreenContent(state, listener)
}

@Composable
private fun HistoryScreenContent(
    state: State,
    listener: Listener?
) = Column(modifier = Modifier.fillMaxSize()) {
    ProfileNavBar(
        userImage = state.user.userImage,
        actions = { ActionButton(iconId = R.drawable.ic_clear) {} },
        onUserClick = { listener?.onProfileClick() }
    )
}

@Preview(showBackground = true)
@Composable
private fun PreviewHistory() = MentalMeTheme {
    HistoryScreenContent(State(), null)
}