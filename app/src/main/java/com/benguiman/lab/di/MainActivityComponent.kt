package com.benguiman.lab.di

import com.benguiman.lab.ui.MainActivity
import com.benguiman.lab.ui.Navigator
import com.benguiman.lab.ui.menu_screen.MenuFragment
import com.benguiman.lab.ui.third_screen.ThirdFragment
import dagger.BindsInstance
import dagger.Subcomponent

@ActivityScope
@Subcomponent(modules = [MainActivityModule::class])
interface MainActivityComponent {

    @Subcomponent.Builder
    interface Builder {
        @BindsInstance
        fun navigator(navigator: Navigator): Builder

        fun build(): MainActivityComponent
    }

    fun firstFragmentComponentBuilder(): FirstFragmentComponent.Builder

    fun secondFragmentComponentBuilder(): SecondFragmentComponent.Builder

    fun inject(mainActivity: MainActivity)

    fun inject(fragment: MenuFragment)

    fun inject(fragment: ThirdFragment)
}