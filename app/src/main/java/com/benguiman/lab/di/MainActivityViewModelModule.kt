package com.benguiman.lab.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.benguiman.lab.ui.third_screen.ThirdScreenViewModel
import com.benguiman.lab.ui.third_screen.ViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class MainActivityViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(ThirdScreenViewModel::class)
    internal abstract fun thirdScreenViewModel(viewModel: ThirdScreenViewModel): ViewModel
}