package com.wagnod.dashboard.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.skydoves.landscapist.coil.CoilImage
import com.wagnod.core.datastore.articles.Article
import com.wagnod.core.datastore.articles.Daily
import com.wagnod.core_ui.EffectObserver
import com.wagnod.core_ui.navigation.ProfileNavBar
import com.wagnod.core_ui.navigation.ActionButton
import com.wagnod.core_ui.navigators.main.Navigator
import com.wagnod.core_ui.sheet.BottomSheetContent
import com.wagnod.core_ui.sheet.data.BottomSheetParams
import com.wagnod.core_ui.theme.*
import com.wagnod.core_ui.R
import com.wagnod.dashboard.ui.DashboardContract.*
import org.koin.androidx.compose.getViewModel

@Composable
fun DashboardMainScreen(
    navigator: Navigator,
    viewModel: DashboardViewModel = getViewModel()
) {
    val state = viewModel.viewState.value

    val params = BottomSheetParams(
        content = BottomSheetContent(
            name = state.user.name,
            image = state.user.userImage,
            onGenClick = { viewModel.setEvent(Event.OnGeneralSheetClick(it)) },
            onFeedbackClick = { viewModel.setEvent(Event.OnFeedbackSheetClick(it)) }
        )

    )
    val listener = object : Listener {
        override fun onProfileClick() {
            viewModel.setEvent(Event.ShowBottomSheet(params))
        }

        override fun onArticleClick(article: Article) {
            viewModel.setEvent(Event.OnArticleClick(article))
        }

        override fun onDailyClick(daily: Daily) {
            TODO("Not yet implemented")
        }
    }

    LaunchedEffect(Unit) {
        viewModel.setEvent(Event.Init)
    }


    EffectObserver(viewModel.effect) {
        when (it) {
            is Effect.ShowFullArticle -> {
                navigator.dashboardNavigator.navigateToArticle(it.article)
            }
        }
    }

    DashboardScreenContent(state, listener)
}

@Composable
private fun DashboardScreenContent(
    state: State,
    listener: Listener?
) = Column(
    modifier = Modifier
        .fillMaxSize()
        .background(color = MaterialTheme.colors.background)
) {
    ProfileNavBar(
        userImage = state.user.userImage,
        actions = { ActionButton(R.drawable.ic_search) {} },
        onUserClick = { listener?.onProfileClick() }
    )
    LazyColumn() {
        item {
            SectionTodaySelection(state, listener)
        }
        item {
            SectionDailies(state, listener)
        }
    }
}

@Composable
private fun SectionTodaySelection(
    state: State,
    listener: Listener?
) = Column(
    modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 8.dp)
) {
    Text(
        text = "Today's\nSelection",
        color = MaterialTheme.colors.textPrimary,
        style = MaterialTheme.typography.largeTitle,
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp)
    )
    Spacer(modifier = Modifier.padding(16.dp))
    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        state.articles.forEach {
            item {
                SectionCard(
                    image = it.image,
                    title = it.title,
                    onClick = { listener?.onArticleClick(it) },
                    isDaily = false
                )
            }
        }
    }
}

@Composable
private fun SectionDailies(
    state: State,
    listener: Listener?
) = Column(
    modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 8.dp)
) {
    Text(
        text = "Explore Dailies",
        color = MaterialTheme.colors.textPrimary,
        style = MaterialTheme.typography.title2,
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp)
    )
    Spacer(modifier = Modifier.padding(16.dp))
    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        state.dailies.forEach {
            item {
                SectionCard(
                    image = it.image,
                    title = it.title,
                    onClick = { listener?.onDailyClick(it) },
                    isDaily = true
                )
            }
        }
    }
}

@Composable
private fun SectionCard(
    image: Any?,
    title: String,
    onClick: () -> Unit,
    isDaily: Boolean
) {
    val width = (LocalConfiguration.current.screenWidthDp.dp - 44.dp) / 2

    Column(
        modifier = Modifier
            .padding(start = 4.dp, end = 4.dp, bottom = 12.dp)
            .width(width)
            .shadow(
                elevation = 4.dp,
                shape = RoundedCornerShape(4.dp),
                ambientColor = MaterialTheme.colors.shadowsPrimary,
                spotColor = MaterialTheme.colors.shadowsPrimary
            )
            .clickable {
                onClick.invoke()
            },
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CoilImage(
            imageModel = { image },
            modifier = Modifier
                .padding(top = 16.dp)
                .size(120.dp)
        )
        Spacer(modifier = Modifier.height(6.dp))
        if (isDaily) {
            Text(
                modifier = Modifier.padding(bottom = 2.dp),
                text = "Daily",
                style = MaterialTheme.typography.subheadline,
                color = MaterialTheme.colors.textColorSecondary
            )
        }
        Text(
            modifier = Modifier.padding(bottom = 16.dp),
            text = title,
            style = MaterialTheme.typography.headline,
            color = MaterialTheme.colors.textPrimary
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun Preview() = MentalMeTheme {
    DashboardScreenContent(state = State(), listener = null)
}
