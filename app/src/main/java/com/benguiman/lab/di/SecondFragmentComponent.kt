package com.benguiman.lab.di

import com.benguiman.lab.ui.second_screen.SecondFragment
import dagger.Subcomponent

@FragmentScope
@Subcomponent
interface SecondFragmentComponent {

    @Subcomponent.Builder
    interface Builder {
        fun build(): SecondFragmentComponent
    }

    fun inject(secondFragment: SecondFragment)
}