package com.beatrice.greetingsapp.ui.view

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel

class MyViewModel : ViewModel() {

    val name = mutableStateOf(" ")
    val isVisible = mutableStateOf(true)
    val greetings = mutableStateOf("What's your sweet name?")

    fun onTextChanged(myName: String){
        name.value = myName
    }

}