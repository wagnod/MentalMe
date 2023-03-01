package com.wagnod.home.goals.di

import com.wagnod.home.goals.GoalsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val goalsModule = module {
    viewModel { GoalsViewModel(get(), get()) }
}
