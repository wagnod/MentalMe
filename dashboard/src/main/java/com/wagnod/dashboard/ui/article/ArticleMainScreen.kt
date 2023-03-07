package com.wagnod.dashboard.ui.article

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.skydoves.landscapist.coil.CoilImage
import com.wagnod.core.datastore.articles.Article
import com.wagnod.core.datastore.articles.ArticleType
import com.wagnod.core_ui.R
import com.wagnod.core_ui.navigation.BackButton
import com.wagnod.core_ui.navigation.NavBar
import com.wagnod.core_ui.navigation.NavBarTitle
import com.wagnod.core_ui.navigators.main.Navigator
import com.wagnod.core_ui.theme.graphicsLight
import com.wagnod.core_ui.theme.textPrimary
import com.wagnod.dashboard.ui.article.ArticleContract.*
import org.koin.androidx.compose.getViewModel

@Composable
fun ArticleMainScreen(
    article: Article,
    navigator: Navigator,
    viewModel: ArticleViewModel = getViewModel()
) {
    val state = viewModel.viewState.value

    val listener = object : Listener {
        override fun onBack() {
            navigator.back()
        }
    }

    LaunchedEffect(Unit) {
        viewModel.setEvent(Event.Init(article))
    }
    ArticleScreenContent(state, listener)
}

@Composable
private fun ArticleScreenContent(
    state: State,
    listener: Listener
) = LazyColumn(
    modifier = Modifier.fillMaxSize()
) {
    items(1) {
        NavBar(
            navigationIcon = { BackButton { listener.onBack() } },
            title = { NavBarTitle(title = state.article.title) }
        )
        TopImage(state.article.image, state.article.type == ArticleType.AUDIO)
        Description(state.article.description)
    }
}

@Composable
private fun Description(description: String) = Text(
    text = description,
    style = MaterialTheme.typography.body1,
    color = MaterialTheme.colors.textPrimary,
    modifier = Modifier
        .fillMaxWidth()
        .padding(top = 8.dp, start = 16.dp, end = 16.dp)
)

@Composable
fun TopImage(
    image: String,
    isAudio: Boolean
) = ConstraintLayout(
    modifier = Modifier
        .fillMaxWidth()
        .height(250.dp)
) {
    val (picture, badge) = createRefs()

    val imageModifier = Modifier.constrainAs(picture) {
        top.linkTo(parent.top)
        bottom.linkTo(parent.bottom)
        start.linkTo(parent.start)
        end.linkTo(parent.end)
    }
    CoilImage(
        imageModel = { image },
        modifier = imageModifier
    )
    val badgeModifier = Modifier
        .constrainAs(badge) {
            top.linkTo(parent.top, 16.dp)
            end.linkTo(parent.end, 16.dp)
        }
    if (isAudio) {
        IconButton(
            onClick = { /*TODO*/ },
            modifier = badgeModifier
                .size(48.dp)
                .clip(CircleShape)
                .background(color = MaterialTheme.colors.graphicsLight)
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_listen),
                contentDescription = "",
                modifier = Modifier.size(24.dp)
            )
        }
    }
}