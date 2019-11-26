package com.benguiman.lab.di

import com.benguiman.lab.ui.first_screen.FirstFragment
import com.benguiman.lab.ui.first_screen.FirstView
import dagger.BindsInstance
import dagger.Subcomponent

@FragmentScope
@Subcomponent
interface FirstFragmentComponent {

    @Subcomponent.Builder
    interface Builder {
        @BindsInstance
        fun firstView(firstView: FirstView): Builder

        fun build(): FirstFragmentComponent
    }

    fun inject(firstFragment: FirstFragment)
}