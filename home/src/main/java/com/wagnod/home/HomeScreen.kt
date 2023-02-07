package com.wagnod.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.skydoves.landscapist.coil.CoilImage
import com.wagnod.core_ui.navigators.main.Navigator
import com.wagnod.home.HomeContract.*
import com.wagnod.home.data.HomeCardData
import com.wagnod.home.data.HomeCardType
import com.wagnod.home.data.HomeCardType.*
import com.wagnod.home.data.getCardsData
import org.koin.androidx.compose.getViewModel

@Composable
fun HomeScreen(
    navigator: Navigator,
    viewModel: HomeViewModel = getViewModel()
) {
    val state = viewModel.viewState.value

    val listener = object : Listener {

        override fun onCardClick(type: HomeCardType) {
            when (type) {
                TRACKER -> viewModel.setEvent(Event.OnTrackerClick)
                GOALS -> viewModel.setEvent(Event.OnGoalsClick)
                DIARY -> viewModel.setEvent(Event.OnDiaryClick)
            }
        }

        override fun onProfileClick() {
            viewModel.setEvent(Event.OnProfileClick(state.user))
        }
    }

    LaunchedEffect(true) {
        viewModel.effect.collect {
            when (it) {
                is Effect.NavigateToTracker -> navigator.homeNavigator.navigateToTracker()
                is Effect.NavigateToDiary -> navigator.homeNavigator.navigateToDiary()
                is Effect.NavigateToGoals -> navigator.homeNavigator.navigateToGoals()
                is Effect.ShowProfile -> {} //TODO
            }
        }
    }

    HomeScreenContent(state = state, listener = listener)
}

@Composable
private fun HomeScreenContent(
    state: State,
    listener: Listener?
) = Column(modifier = Modifier.fillMaxSize()) {
    Toolbar(state, listener)
    HomeCards(listener)
}


@Composable
private fun Toolbar(
    state: State,
    listener: Listener?
) = Row(
    verticalAlignment = Alignment.CenterVertically,
    modifier = Modifier
        .fillMaxWidth()
        .height(56.dp)
        .padding(start = 16.dp)
) {
    CoilImage(
        imageModel = {
            state.user.userImage.ifEmpty { R.drawable.ic_profile_icon }
        },
        modifier = Modifier
            .size(32.dp)
            .clip(RoundedCornerShape(4.dp))
            .clickable(
                indication = null,
                interactionSource = MutableInteractionSource()
            ) {
                listener?.onProfileClick()
            }
    )
}


@Composable
private fun HomeCards(listener: Listener?) {
    Column(
        modifier = Modifier.fillMaxSize(),
    ) {
        getCardsData().forEachIndexed { index, it ->
            HomeScreenCard(
                index = index,
                card = it,
                listener = listener
            )
        }
    }
}


@Composable
private fun HomeScreenCard(
    index: Int,
    card: HomeCardData,
    listener: Listener?
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 150.dp)
            .clickable { }
            .padding(top = if (index == 0) 0.dp else 5.dp, bottom = 5.dp)
            .shadow(elevation = 2.dp)
            .clickable { listener?.onCardClick(card.type) }

    ) {
        Image(
            painter = painterResource(card.icon),
            contentDescription = "",
            modifier = Modifier
                .padding(start = 16.dp)
                .size(70.dp)
                .align(alignment = Alignment.Companion.CenterVertically),
        )

        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
                .align(alignment = Alignment.Companion.CenterVertically)
        ) {
            Text(
                text = card.name,
                style = TextStyle(
                    color = Color.Black,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.SansSerif
                )
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = card.text,
            )
        }
    }
}