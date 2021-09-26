package com.beatrice.greetingsapp.ui.view.composables

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.beatrice.greetingsapp.ui.view.MyViewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState

@ExperimentalPagerApi
@Composable
fun Profile(
    name: String,
    state: PagerState,
    btnText: String,
    onButtonClicked: () -> Unit,
    question: String,
    onTextChanged: (String) -> Unit,
    answer: String,
    currentIndex: Int
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (currentIndex == 0) {
            Text(
                text = "Welcome $name!",
                fontFamily = FontFamily.Monospace,
                color = Color.DarkGray,
                fontSize = 22.sp
            )
            Text(
                text = "Let's get to know you.",
                modifier = Modifier.padding(vertical = 10.dp),
                fontFamily = FontFamily.Monospace,
                color = Color.DarkGray,
                fontSize = 20.sp,
                // TODO: Add Icon with InlineTextContent
            )
        }
        MyPager(
            pagerState = state,
            btnText = btnText,
            onButtonClicked = onButtonClicked,
            question = question,
            onTextChanged = onTextChanged,
            answer = answer,
        )

    }
}


@ExperimentalPagerApi
@Composable
fun MyPager(
    pagerState: PagerState,
    btnText: String,
    onButtonClicked: () -> Unit,
    question: String,
    onTextChanged: (String) -> Unit,
    answer: String
) {
    HorizontalPager(
        state = pagerState,
        dragEnabled = false
    ) { page ->
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 64.dp, bottom = 64.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = question,
                modifier = Modifier.fillMaxWidth(),
                fontFamily = FontFamily.Monospace,
                textAlign = TextAlign.Center,
                color = Color.DarkGray,
                fontSize = 20.sp

            )
            TextField(
                value = answer,
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
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = onButtonClicked) {
                Text(text = btnText)

            }
        }

    }
}