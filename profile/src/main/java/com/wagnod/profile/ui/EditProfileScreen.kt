package com.wagnod.profile.ui
//
//import androidx.compose.foundation.Image
//import androidx.compose.foundation.background
//import androidx.compose.foundation.clickable
//import androidx.compose.foundation.layout.*
//import androidx.compose.material.MaterialTheme
//import androidx.compose.material.Text
//import androidx.compose.material.TextField
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.res.painterResource
//import androidx.compose.ui.text.TextStyle
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import androidx.constraintlayout.compose.ConstraintLayout
//import androidx.constraintlayout.compose.Dimension
//import com.wagnod.profile.ui.ProfileContract
//import com.wagnod.profile.ui.ProfileContract.Listener
//import com.wagnod.profile.ui.ProfileContract.State
//import com.wagnod.profile.ui.ProfileViewModel
//import com.wagnod.profile.R.drawable
//import com.wagnod.profile.data.User
//import org.koin.androidx.compose.getViewModel
//
//@Composable
//fun EditProfileScreen(
//    viewModel: ProfileViewModel = getViewModel()
//) {
//    val listener = object : Listener {
//        override fun onDataChanged(user: User) {
//            viewModel.setEvent(ProfileContract.Event.OnDataChanged(user))
//        }
//    }
//
//    val state = viewModel.viewState.value
//}
//
//@Composable
//fun EditProfileScreenContent(
//    state: State,
//    listener: Listener
//) = Column(modifier = Modifier.fillMaxSize()) {
//    TopAppBar()
//    EditUserImage()
//    EditUserName(state = state, listener = listener)
//    EditStatus(state = state, listener = listener)
//}
//
//@Composable
//fun TopAppBar() = ConstraintLayout(
//    modifier = Modifier
//        .fillMaxWidth()
//        .background(MaterialTheme.colors.primary)
//        .height(56.dp)
//        .padding(horizontal = 16.dp),
//
//    ) {
//    val (back, title, save) = createRefs()
//    Image(
//        painter = painterResource(id = drawable.ic_back),
//        contentDescription = "",
//        modifier = Modifier
//            .size(24.dp)
//            .constrainAs(back) {
//                top.linkTo(parent.top)
//                bottom.linkTo(parent.bottom)
//                start.linkTo(parent.start)
//                end.linkTo(title.start)
//            }
//            .clickable { /* TODO */ }
//    )
//    Text(
//        text = "Добавить цель",
//        modifier = Modifier
//            .padding(start = 24.dp)
//            .constrainAs(title) {
//                width = Dimension.fillToConstraints
//                top.linkTo(parent.top)
//                bottom.linkTo(parent.bottom)
//                start.linkTo(back.end)
//                end.linkTo(save.start)
//            },
//        style =
//        TextStyle(
//            color = Color.White,
//            fontSize = 20.sp,
//            fontWeight = FontWeight.Medium
//        )
//    )
//    Image(
//        painter = painterResource(id = drawable.ic_confirm),
//        contentDescription = "",
//        modifier = Modifier
//            .size(24.dp)
//            .constrainAs(save) {
//                top.linkTo(parent.top)
//                bottom.linkTo(parent.bottom)
//                start.linkTo(title.end)
//                end.linkTo(parent.end)
//            }
//            .clickable { /* TODO */ }
//    )
//}
//
//
//@Composable
//fun EditUserImage() {
//    Image(
//        painter = painterResource(id = drawable.ic_profile_icon),
//        contentDescription = "user image"
//    )
//}
//
//@Composable
//fun EditUserName(
//    state: State,
//    listener: Listener
//) {
//    TextField(
//        modifier = Modifier.fillMaxWidth(),
//        value = state.user.name,
//        onValueChange = { value ->
//            listener.onDataChanged(
//                user.copy (
//                    profileImage = state.profileImage,
//                    name = value,
//                    id = state.id,
//                    status = state.status
//                )
//            )
//        }
//    )
//}
//
//@Composable
//private fun EditStatus(
//    state: State,
//    listener: Listener
//) {
//    TextField(
//        modifier = Modifier.fillMaxWidth(),
//        value = state.status,
//        onValueChange = { value ->
//            listener.onDataChanged(
//                User(
//                    profileImage = state.profileImage,
//                    name = state.name,
//                    id = state.id,
//                    status = value
//                )
//            )
//        }
//    )
//}