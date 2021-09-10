package com.beatrice.greetingsapp.ui.view

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.beatrice.greetingsapp.ui.theme.GreetingsAppTheme
import org.koin.androidx.compose.getViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GreetingsAppTheme {
                Surface(color = MaterialTheme.colors.background) {
                    Greeting()
                }
            }
        }
    }
}

@Composable
fun Greeting() {
    val viewModel: MyViewModel = getViewModel()

    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val name = remember { mutableStateOf("") }
        val isVisible = remember{ mutableStateOf(true)}
        val greeting = remember{ mutableStateOf("What's your sweet name?")}
        val visible = viewModel.isVisible.value
        Text(
//            text = viewModel.greetings.value,
            text = greeting.value,
            fontFamily = FontFamily.Monospace,
            color = Color.DarkGray,
            fontSize = 22.sp
        )

        if (isVisible.value) {
            TextField(
                value = name.value,
//                value = viewModel.name.value,
                onValueChange = { newValue ->
                    Log.d("NAME", newValue)
//                    viewModel.name.value = newValue
                    name.value = newValue
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 42.dp),
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.White,
                    focusedIndicatorColor = Color.DarkGray,
                    unfocusedIndicatorColor = Color.DarkGray
                ),
            )
            Button(
                onClick = {
                    greeting.value = "Welcome ${name.value}!"
                    isVisible.value = !isVisible.value
                    viewModel.greetings.value = "Welcome ${viewModel.name.value}!"
                    viewModel.isVisible.value = !visible
                },
                modifier = Modifier.padding(24.dp)
            ) {
                Text(text = "OK")
            }
        }

    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    GreetingsAppTheme {
        Greeting()
    }
}