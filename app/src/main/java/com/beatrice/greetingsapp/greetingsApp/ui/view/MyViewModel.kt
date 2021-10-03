package com.beatrice.greetingsapp.greetingsApp.ui.view

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.beatrice.greetingsapp.greetingsApp.data.Question
import com.beatrice.greetingsapp.greetingsApp.data.QuestionType
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerState
import kotlinx.coroutines.launch

class MyViewModel : ViewModel() {

    val name = mutableStateOf(" ")
    val greetings = mutableStateOf("What's your sweet name?")

    val questions = mutableListOf(
        Question(
            questionText = "Did you go to the Movie last night?",
            type = QuestionType.MULTIPLE_CHOICE
        ),
        Question(
            questionText = "What are your hobbies?",
            type = QuestionType.OPEN_ENDED
        ),
        Question(
            questionText = "What's you favourite meal?",
            type = QuestionType.OPEN_ENDED
        ),
        Question(
            questionText = "Do you love dogs?",
            type = QuestionType.MULTIPLE_CHOICE
        ),
        Question(
            questionText = "What's your favourite destination?",
            type = QuestionType.OPEN_ENDED
        )

    )
    val question = mutableStateOf(questions.elementAt(0))
    val currentIndex = mutableStateOf(0)
    val btnText = mutableStateOf("Next")
    val answer = mutableStateOf(" ")
    val isLastQuestion = mutableStateOf(false)

    fun onTextChanged(myName: String) {
        name.value = myName
    }

    @ExperimentalPagerApi
    fun getNextQuestion(pager: PagerState) {
        viewModelScope.launch {
            Log.d("Current", "index => ${currentIndex.value}")
            val nextIndex = currentIndex.value + 1
            currentIndex.value = nextIndex

            if (nextIndex+1 == questions.size) {
                btnText.value = "Finish"
               isLastQuestion.value = true
            }
            pager.scrollToPage(nextIndex)
            question.value = questions.elementAt(nextIndex)
            answer.value = questions.elementAt(nextIndex).answer!!
        }
    }

    // TODO: Rename appropriately
    fun finish(){
        Log.d("ALLL", "Answers $questions")
    }

    fun getAnswer(newAnswer: String, quiz: Question) {
        Log.d("Answer", "is => $newAnswer")
        answer.value = newAnswer
        quiz.answer = newAnswer
    }

}