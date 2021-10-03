package com.beatrice.greetingsapp.greetingsApp.data

data class Question(
    val questionText: String,
    val type: QuestionType,
    var answer: String? = null
)