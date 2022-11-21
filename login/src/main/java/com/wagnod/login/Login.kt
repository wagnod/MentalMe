package com.wagnod.login

import android.widget.Space
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.wagnod.core_ui.theme.MentalMeTheme

@Composable
private fun LoginPage() = ConstraintLayout(
    modifier = Modifier.fillMaxSize(),
) {
    val (card, text) = createRefs()
    Column(
        modifier = Modifier.
            constrainAs(card) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                bottom.linkTo(parent.bottom)
            },
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Title()
        EmailInputView()
        PasswordInputView()
        LoginButton()
        ForgotPasswordText()
    }
    val textModifier = Modifier.constrainAs(text) {
//        top.linkTo(card.bottom)
        bottom.linkTo(parent.bottom)
        start.linkTo(parent.start)
        end.linkTo(parent.end)
    }
    SignUpText(textModifier)
}

@Composable
private fun Title() {
    Text(
        modifier = Modifier.padding(vertical = 10.dp),
        text = "Sign in",
        style = TextStyle(fontSize = 30.sp)
    )
}

@Composable
private fun EmailInputView() {
    val username = remember { mutableStateOf(TextFieldValue()) }
    TextField(
        modifier = Modifier.padding(vertical = 10.dp),
        label = { Text(text = "Email") },
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Email,
                contentDescription = ""
            )
        },
        value = username.value,
        onValueChange = { username.value = it }
    )
}

@Composable
private fun PasswordInputView() {
    val password = remember { mutableStateOf(TextFieldValue()) }
    TextField(
        modifier = Modifier.padding(vertical = 10.dp),
        label = { Text(text = "Password") },
        value = password.value,
        visualTransformation = PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        onValueChange = { password.value = it }
    )
}

@Composable
private fun LoginButton() {
    Button(
        onClick = { },
        shape = RoundedCornerShape(50.dp),
        modifier = Modifier
            .padding(horizontal = 50.dp, vertical = 10.dp)
            .fillMaxWidth()
            .height(50.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = MaterialTheme.colors.primary
        )
    )
    {
        Text(text = "Login")
    }
}

@Composable
private fun ForgotPasswordText() {
    ClickableText(
        text = AnnotatedString("Forgot password?"),
        modifier = Modifier.padding(vertical = 10.dp),
        onClick = { },
        style = TextStyle(
            fontSize = 14.sp
        )
    )
}

@Composable
private fun SignUpText(modifier: Modifier) {
    ClickableText(
        text = AnnotatedString("Sign up here"),
        modifier = modifier.padding(bottom = 32.dp),
        onClick = { },
        style = TextStyle(
            fontSize = 14.sp,
            color = MaterialTheme.colors.primary,
            textDecoration = TextDecoration.Underline
        )
    )
}

@Preview(showBackground = true)
@Composable
private fun PreviewLogin() {
    MentalMeTheme {
        LoginPage()
    }
}