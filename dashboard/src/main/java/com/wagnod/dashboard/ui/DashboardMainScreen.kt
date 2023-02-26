package com.wagnod.dashboard.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.skydoves.landscapist.coil.CoilImage
import com.wagnod.core_ui.navigators.main.Navigator
import com.wagnod.core_ui.theme.MentalMeTheme
import com.wagnod.core_ui.theme.headline
import com.wagnod.core_ui.theme.largeTitle
import com.wagnod.core_ui.theme.textPrimary
import com.wagnod.dashboard.ui.DashboardContract.*
import com.wagnod.domain.UserInfo
import org.koin.androidx.compose.getViewModel

@Composable
fun DashboardMainScreen(
    navigator: Navigator,
    viewModel: DashboardViewModel = getViewModel()
) {
    val state = viewModel.viewState.value

    val listener = object : Listener {
        override fun onProfileClick(user: UserInfo) {
            viewModel.setEvent(Event.OnProfileClick(user))
        }

        override fun onArticleClick(article: Article) {
            viewModel.setEvent(Event.OnArticleClick(article))
        }

    }

    LaunchedEffect(true) {
        viewModel.effect.collect {

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
            .size(32.dp)
            .clip(CircleShape)
            .clickable(
                indication = null,
                interactionSource = MutableInteractionSource()
            ) {
                listener?.onProfileClick(state.user)
            }
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

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        state.articles.forEach {
            SectionArticle(article = it, listener = listener)
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
            .padding(bottom = 12.dp)
            .width(width)
            .shadow(
                elevation = 4.dp,
                shape = RoundedCornerShape(8.dp),
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