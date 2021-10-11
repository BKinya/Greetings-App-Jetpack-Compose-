package com.beatrice.greetingsapp.greetingsApp.ui.view.composables

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.beatrice.greetingsapp.greetingsApp.data.Question
import com.beatrice.greetingsapp.greetingsApp.data.QuestionType
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState

@ExperimentalPagerApi
@Composable
fun Profile(
    name: String,
    state: PagerState,
    btnText: String,
    onAnswered: (String) -> Unit,
    question: Question,
    currentIndex: Int,
    onButtonClicked: () -> Unit,
    answer: String
) {
    Log.d("Answer", "Does it really recreate this")
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
            onAnswered = onAnswered,
            question = question,
            onButtonClicked = onButtonClicked,
            answer = answer
        )

    }
}


@ExperimentalPagerApi
@Composable
fun MyPager(
    pagerState: PagerState,
    btnText: String,
    onAnswered: (String) -> Unit,
    onButtonClicked: () -> Unit,
    question: Question,
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
                text = question.questionText,
                modifier = Modifier.fillMaxWidth(),
                fontFamily = FontFamily.Monospace,
                textAlign = TextAlign.Center,
                color = Color.DarkGray,
                fontSize = 20.sp
            )
            ShowAnswerContainer(question = question, onAnswered = onAnswered, answer = answer)
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = onButtonClicked) {
                Text(text = btnText)

            }
        }
    }
}

@Composable
fun ShowAnswerContainer(
    question: Question,
    onAnswered: (String) -> Unit,
    answer: String
) {
    when (question.type) {
        QuestionType.OPEN_ENDED -> {
            OpenEndedAnsContainer(answer = answer, onAnswered = onAnswered)
        }
        QuestionType.MULTIPLE_CHOICE -> {
            MultipleChoicesContainer(onOptionSelected = onAnswered, answer )
        }
    }
}