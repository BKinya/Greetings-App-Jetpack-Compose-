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
import com.beatrice.greetingsapp.ui.theme.GreetingsAppTheme
import org.koin.androidx.compose.getViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val viewModel: MyViewModel = getViewModel()
            val name = viewModel.name.value
            val greetings = viewModel.greetings.value
            val isVisible = viewModel.isVisible.value
            GreetingsAppTheme {
                Surface(color = MaterialTheme.colors.background) {
                    Greeting(
                        greetings = greetings, name = name, isVisible = isVisible,
                        onButtonClicked = viewModel::onButtonClicked,
                        onTextChanged = viewModel::onTextChanged
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(
    greetings: String,
    name: String,
    isVisible: Boolean,
    onTextChanged: (String) -> Unit,
    onButtonClicked: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = greetings,
            fontFamily = FontFamily.Monospace,
            color = Color.DarkGray,
            fontSize = 22.sp
        )

        if (isVisible) {
            TextField(
                value = name,
                onValueChange = onTextChanged,
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
                onClick = onButtonClicked,
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
    }
}