package com.elnico.hotelpromo.di

import com.elnico.hotelpromo.ui.DetailsViewModel
import com.elnico.hotelpromo.ui.auth.signup.SignUpViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

fun getViewModelModule() = module {
    viewModel<SignUpViewModel> { SignUpViewModel(get()) }
    viewModel<DetailsViewModel> { DetailsViewModel(get()) }
}