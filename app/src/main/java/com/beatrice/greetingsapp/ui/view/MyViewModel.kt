package com.beatrice.greetingsapp.ui.view

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerState
import kotlinx.coroutines.launch

class MyViewModel : ViewModel() {

    val name = mutableStateOf(" ")
    val greetings = mutableStateOf("What's your sweet name?")

    val questions = mutableMapOf(
        "Did you go to the Movie last night?" to " ",
        "What are your hobbies?" to " ",
        "What's you favourite meal?" to " ",
        "Do you love dogs?" to " ",
        "What's your favourite destination?" to " "

    )
    val question = mutableStateOf(questions.keys.elementAt(0))
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
            question.value = questions.keys.elementAt(nextIndex)
            answer.value = questions.values.elementAt(nextIndex)
        }
    }

    // TODO: Rename appropriately
    fun finish(){
        Log.d("ALLL", "Answers $questions")
    }

    fun getAnswer(newAnswer: String) {
        Log.d("Answer", "is => $newAnswer")
        answer.value = newAnswer
        val quest = question.value
        questions[quest] = newAnswer
    }

}