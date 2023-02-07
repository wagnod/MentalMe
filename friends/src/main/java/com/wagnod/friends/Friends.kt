package com.wagnod.friends

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.wagnod.core_ui.navigation.NavBar
import com.wagnod.core_ui.navigation.NavBarTitle
import com.wagnod.core_ui.theme.MentalMeTheme

@Composable
fun FriendsScreenContent(
    friends: List<Friend> = sampleData
) = Column(modifier = Modifier.fillMaxSize()) {
    NavBar(title = { NavBarTitle(title = "Друзья") })
    LazyColumn {
        friends.map { item { FriendCard(it) } }
    }
}

data class Friend(val firstName: String, val lastName: String, val status: String)

@Composable
private fun FriendCard(friend: Friend) {
    ConstraintLayout(
        modifier = Modifier.fillMaxWidth()
    ) {
        val (box, button) = createRefs()
        Row(
            modifier = Modifier
                .constrainAs(box) {
                    width = Dimension.fillToConstraints
                    start.linkTo(parent.start)
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    end.linkTo(button.start)
                }
                .clickable { }
                .padding(horizontal = 16.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(R.drawable.ic_profile_icon),
                contentDescription = "Contact profile picture",
                modifier = Modifier
                    .size(50.dp)
                    .clip(CircleShape)
            )

            Column(
                modifier = Modifier.padding(start = 16.dp)
            ) {
                Text(
                    text = friend.firstName + " " + friend.lastName
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = friend.status,
                    style = TextStyle(color = Color.Gray, fontSize = 11.sp)
                )
            }
        }
        Box(
            modifier = Modifier
                .clickable { }
                .constrainAs(button) {
                    height = Dimension.fillToConstraints
                    end.linkTo(parent.end)
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                },
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(R.drawable.ic_chat),
                contentDescription = "Message icon",
                modifier = Modifier
                    .padding(horizontal = 16.dp, vertical = 8.dp)
                    .size(30.dp)
            )
        }

    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewFriends() {
    val sampleData = listOf(
        Friend("Varvara", "Symonovych", "Fill Depressed"),
        Friend("Varvara", "Symonovych", "Fill Depressed")
    )
    MentalMeTheme {
        FriendsScreenContent(sampleData)
    }
}


private val sampleData = listOf(
    Friend("Varvara", "Symonovych", "Fill Depressed"),
    Friend("Varvara", "Symonovych", "Fill Depressed")
)