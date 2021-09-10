package com.beatrice.greetingsapp.ui.di

import com.beatrice.greetingsapp.ui.view.MyViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModules = module {
    viewModel { MyViewModel() }
}