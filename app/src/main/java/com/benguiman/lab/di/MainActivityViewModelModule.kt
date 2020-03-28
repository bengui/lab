package com.benguiman.lab.di

import androidx.lifecycle.ViewModel
import com.benguiman.lab.ui.MainViewModel
import com.benguiman.lab.ui.third_screen.ThirdScreenViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class MainActivityViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(ThirdScreenViewModel::class)
    internal abstract fun thirdScreenViewModel(viewModel: ThirdScreenViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    internal abstract fun mainViewModel(viewModel: MainViewModel): ViewModel
}