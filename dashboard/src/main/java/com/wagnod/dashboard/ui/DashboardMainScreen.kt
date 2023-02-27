package com.wagnod.dashboard.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.skydoves.landscapist.coil.CoilImage
import com.wagnod.core_ui.EffectObserver
import com.wagnod.core_ui.navigators.main.Navigator
import com.wagnod.core_ui.sheet.BottomSheetHeader
import com.wagnod.core_ui.sheet.SheetButtonItem
import com.wagnod.core_ui.sheet.UserInfoBottomSheet
import com.wagnod.core_ui.sheet.data.BottomSheetParams
import com.wagnod.core_ui.sheet.data.FeedbackSheetItems
import com.wagnod.core_ui.sheet.data.GeneralSheetItems
import com.wagnod.core_ui.theme.*
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
    }

    LaunchedEffect(Unit) {
        viewModel.setEvent(Event.Init)
    }


    EffectObserver(viewModel.effect) {
        when (it) {
            is Effect.ShowFullArticle -> {}
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
    Toolbar(state, listener)
    Section(state, listener)
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
            state.user.userImage
        },
        modifier = Modifier
            .clickable {
                listener?.onProfileClick()
            }
            .size(40.dp)
            .clip(RoundedCornerShape(4.dp))
    )
}

@Composable
private fun Section(
    state: State,
    listener: Listener?
) = Column(
    modifier = Modifier
        .fillMaxSize()
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
            item { SectionArticle(article = it, listener = listener) }
        }
    }
}

@Composable
fun SectionArticle(
    article: Article,
    listener: Listener?
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
                listener?.onArticleClick(article)
            },
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CoilImage(
            imageModel = { article.image },
            modifier = Modifier
                .padding(top = 16.dp)
                .size(120.dp)
        )
        Spacer(modifier = Modifier.height(6.dp))
        Text(
            modifier = Modifier.padding(bottom = 16.dp),
            text = article.title,
            style = MaterialTheme.typography.headline,
            color = MaterialTheme.colors.textPrimary
        )
    }
}

@Composable
fun BottomSheetContent(
    name: String,
    image: String,
    onGenClick: (GeneralSheetItems) -> Unit,
    onFeedbackClick: (FeedbackSheetItems) -> Unit,
): @Composable () -> Unit = {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        UserInfoBottomSheet(name, image)
        BottomSheetHeader("General")
        GeneralSheetItems.values().forEach {
            SheetButtonItem(it.title, it.icon) { onGenClick.invoke(it) }
        }
        BottomSheetHeader("Feedback")
        FeedbackSheetItems.values().forEach {
            SheetButtonItem(it.title, it.icon) { onFeedbackClick.invoke(it) }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun Preview() = MentalMeTheme {
    DashboardScreenContent(state = State(), listener = null)
}

data class Article(
    val title: String,
    val image: String,
    val description: String,
    val type: ArticleType
)

enum class ArticleType {
    TEXT, AUDIO
}