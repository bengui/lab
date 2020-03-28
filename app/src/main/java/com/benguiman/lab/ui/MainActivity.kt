package com.benguiman.lab.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.benguiman.lab.ApplicationComponentProvider
import com.benguiman.lab.R
import com.benguiman.lab.di.MainActivityComponent
import com.benguiman.lab.ui.first_screen.FirstFragment
import com.benguiman.lab.ui.menu_screen.MenuFragment
import com.benguiman.lab.ui.second_screen.SecondFragment
import com.benguiman.lab.ui.third_screen.ThirdFragment
import javax.inject.Inject

class MainActivity : AppCompatActivity(), Navigator, MainActivityComponentProvider {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    lateinit var mainViewModel: MainViewModel

    private lateinit var mainActivityComponent: MainActivityComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainActivityComponent = (application as ApplicationComponentProvider)
            .getApplicationComponent()
            .mainActivityComponentBuilder()
            .navigator(this)
            .build()

        mainActivityComponent.inject(this)

        setContentView(R.layout.activity_main)

        mainViewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(MainViewModel::class.java)

        navigateToCurrentScreen()
    }

    private fun navigateToCurrentScreen() {
        when (mainViewModel.currentScreen) {
            CurrentScreen.MAIN_MENU -> navigateToMenuScreen()
            CurrentScreen.FIRST_FRAGMENT -> navigateToFirstScreen()
            CurrentScreen.SECOND_FRAGMENT -> navigateToSecondScreen()
            CurrentScreen.THIRD_FRAGMENT -> navigateToThirdScreen()
        }
    }

    override fun navigateToMenuScreen() {
        navigateTo(MenuFragment(), false)
        mainViewModel.currentScreen = CurrentScreen.MAIN_MENU
    }

    override fun navigateToFirstScreen() {
        navigateTo(FirstFragment())
        mainViewModel.currentScreen = CurrentScreen.FIRST_FRAGMENT
    }

    override fun navigateToSecondScreen() {
        navigateTo(SecondFragment())
        mainViewModel.currentScreen = CurrentScreen.SECOND_FRAGMENT
    }

    override fun navigateToThirdScreen() {
        navigateTo(ThirdFragment())
        mainViewModel.currentScreen = CurrentScreen.THIRD_FRAGMENT
    }

    override fun navigateBack() {
        supportFragmentManager.popBackStack()
    }

    private fun navigateTo(fragment: Fragment, addToBackStack: Boolean = true) {
        with(supportFragmentManager.beginTransaction()) {
            replace(R.id.main_container, fragment)
            if (addToBackStack) {
                addToBackStack(fragment::class.java.simpleName)
            }
            commit()
        }
    }

    override fun getMainActivityComponent() = mainActivityComponent
}
