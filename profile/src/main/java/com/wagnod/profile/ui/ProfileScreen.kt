package com.wagnod.profile.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.skydoves.landscapist.coil.CoilImage
import com.wagnod.core_ui.navigation.NavBar
import com.wagnod.core_ui.navigation.NavBarTitle
import com.wagnod.core_ui.navigators.main.Navigator
import com.wagnod.profile.ui.ProfileContract.*
import com.wagnod.profile.data.User
import org.koin.androidx.compose.getViewModel


@Composable
fun ProfileScreen(
    navigator: Navigator,
    viewModel: ProfileViewModel = getViewModel()
) {
    val listener = object : Listener {
        override fun onDataChanged(user: User) {
            viewModel.setEvent(Event.OnDataChanged(user))
        }
    }

    LaunchedEffect(Unit) {
        viewModel.setEvent(Event.Init)
    }

    ProfileScreenContent(
        state = viewModel.viewState.value,
        listener = listener
    )
}

@Composable
fun ProfileScreenContent(
    state: State,
    listener: Listener
) = Column(
    modifier = Modifier.fillMaxSize()
) {
    NavBar(title = { NavBarTitle(title = "Профиль") })
    UserInfo(state, listener)
}

@Composable
fun UserInfo(
    state: State,
    listener: Listener
) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp)
    ) {
        val (image, card, button) = createRefs()

        val imageModifier = Modifier.constrainAs(image) {
            width = Dimension.fillToConstraints
            top.linkTo(parent.top)
            start.linkTo(parent.start)
            bottom.linkTo(card.top, 16.dp)
        }

        val cardModifier = Modifier.constrainAs(card) {
            width = Dimension.fillToConstraints
            top.linkTo(image.bottom)
            start.linkTo(parent.start)
            bottom.linkTo(parent.bottom)
        }

        val buttonModifier = Modifier.constrainAs(button) {
            end.linkTo(parent.end)
            top.linkTo(parent.top)
            bottom.linkTo(image.bottom)
        }

        UserImage(imageModifier, state)
        UserInfoCard(cardModifier, state)
        EditButton(buttonModifier, listener)
    }
}

@Composable
fun UserImage(
    modifier: Modifier,
    state: State
) = CoilImage(
    modifier = modifier
        .size(75.dp)
        .clip(CircleShape),
    imageModel = { state.user.profileImage }
)

@Composable
fun UserInfoCard(
    modifier: Modifier,
    state: State
) = Column(
    modifier = modifier
) {
    Text(
        text = state.user.name,
        style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Medium)
    )
    Text(
        text = state.user.status,
        style = TextStyle(fontSize = 16.sp)
    )
    Text(
        text = "ID пользователя: " + state.user.id,
        style = TextStyle(fontSize = 16.sp)
    )
}

@Composable
fun EditButton(
    modifier: Modifier,
    listener: Listener
) = Button(
    modifier = modifier,
    shape = RoundedCornerShape(50.dp),
    colors = ButtonDefaults.buttonColors(
        backgroundColor = MaterialTheme.colors.primary.copy(alpha = 0.4f),
        contentColor = MaterialTheme.colors.primary.copy(alpha = 0.9f)
    ),
    onClick = {

    }
) {
    Text(
        text = "Редактировать",
        style = TextStyle(fontSize = 13.sp)
    )
}