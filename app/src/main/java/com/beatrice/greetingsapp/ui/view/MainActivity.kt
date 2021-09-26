package com.beatrice.greetingsapp.ui.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.beatrice.greetingsapp.ui.theme.GreetingsAppTheme
import com.beatrice.greetingsapp.ui.view.composables.Greeting
import com.beatrice.greetingsapp.ui.view.composables.Profile
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState
import org.koin.androidx.compose.getViewModel

class MainActivity : ComponentActivity() {
    @ExperimentalPagerApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val viewModel: MyViewModel = getViewModel()
            val name = viewModel.name.value
            val greetings = viewModel.greetings.value
            val pagerState = PagerState(pageCount = viewModel.questions.size)
            val getNextQuestion = { viewModel.getNextQuestion(pagerState) }
            // TODO: Rename this
            val finish = { viewModel.finish() }
            val question = viewModel.question.value
            val btnText = viewModel.btnText.value
            val answer = viewModel.answer.value
            val currentIndex = viewModel.currentIndex.value
            val isLastQuestion = viewModel.isLastQuestion.value

            val navController = rememberNavController()
            GreetingsAppTheme {
                Scaffold(
                    topBar = {
                        TopAppBar(
                            navigationIcon = {
                                IconButton(onClick = { }) {
                                    Icon(
                                        Icons.Filled.Face,
                                        contentDescription = ""
                                    )
                                }
                            },
                            title = {
                                Text(text = "Greetings")
                            },
                        )
                    },
                    content = {
                        NavHost(
                            navController = navController,
                            startDestination = "greetings",
                            modifier = Modifier.padding(16.dp)
                        ) {
                            composable("greetings") {
                                Greeting(
                                    greetings = greetings,
                                    name = name,
                                    onTextChanged = viewModel::onTextChanged,
                                    onButtonClick = {
                                        navController.navigate("profile") {
                                            popUpTo("greetings") { inclusive = true }
                                        }
                                    }
                                )
                            }
                            composable("profile") {
                                Profile(
                                    name = name,
                                    state = pagerState,
                                    btnText = btnText,
                                    onButtonClicked = if (isLastQuestion) finish else getNextQuestion,
                                    question = question,
                                    onTextChanged = viewModel::getAnswer,
                                    answer = answer,
                                    currentIndex = currentIndex
                                )
                            }
                        }
                    }
                )
            }
        }
    }
}

