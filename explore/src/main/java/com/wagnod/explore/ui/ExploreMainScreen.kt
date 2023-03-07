package com.wagnod.explore.ui

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.wagnod.core_ui.navigation.ProfileNavBar
import com.wagnod.core_ui.navigation.ActionButton
import com.wagnod.core_ui.navigators.main.Navigator
import com.wagnod.core_ui.sheet.BottomSheetContent
import com.wagnod.core_ui.sheet.data.BottomSheetParams
import com.wagnod.core_ui.theme.MentalMeTheme
import com.wagnod.core_ui.R
import com.wagnod.explore.ui.ExploreContract.*
import org.koin.androidx.compose.getViewModel

@Composable
fun ExploreMainScreen(
    navigator: Navigator,
    viewModel: ExploreViewModel = getViewModel()
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

    ExploreScreenContent(state, listener)
}

@Composable
private fun ExploreScreenContent(
    state: State,
    listener: Listener?
) = Column(modifier = Modifier.fillMaxSize()) {
    ProfileNavBar(
        userImage = state.user.userImage,
        actions = { ActionButton(R.drawable.ic_search) {} },
        onUserClick = { listener?.onProfileClick() }
    )
}

@Preview(showBackground = true)
@Composable
private fun ExploreScreenPreview() = MentalMeTheme {
    ExploreScreenContent(State(), null)
}