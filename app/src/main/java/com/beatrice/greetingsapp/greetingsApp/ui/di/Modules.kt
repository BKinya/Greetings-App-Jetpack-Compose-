package com.beatrice.greetingsapp.greetingsApp.ui.di

import com.beatrice.greetingsapp.greetingsApp.ui.view.MyViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModules = module {
    viewModel { MyViewModel() }
}