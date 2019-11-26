package com.benguiman.lab.di

import dagger.Module

@Module(
    subcomponents = [FirstFragmentComponent::class, SecondFragmentComponent::class],
    includes = [MainActivityViewModelModule::class]
)
class MainActivityModule