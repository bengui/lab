package com.benguiman.lab.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.benguiman.lab.ApplicationComponentProvider
import com.benguiman.lab.R
import com.benguiman.lab.di.MainActivityComponent
import com.benguiman.lab.ui.first_screen.FirstFragment
import com.benguiman.lab.ui.menu_screen.MenuFragment
import com.benguiman.lab.ui.second_screen.SecondFragment
import com.benguiman.lab.ui.third_screen.ThirdFragment

class MainActivity : AppCompatActivity(), Navigator, MainActivityComponentProvider {
    private lateinit var mainActivityComponent: MainActivityComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainActivityComponent = (application as ApplicationComponentProvider)
            .getApplicationComponent()
            .mainActivityComponentBuilder()
            .navigator(this)
            .build()
        setContentView(R.layout.activity_main)
        navigateToMenuScreen()
    }

    override fun navigateToMenuScreen() {
        navigateTo(MenuFragment(), false)
    }

    override fun navigateToFirstScreen() {
        navigateTo(FirstFragment())
    }

    override fun navigateToSecondScreen() {
        navigateTo(SecondFragment())
    }

    override fun navigateToThirdScreen() {
        navigateTo(ThirdFragment())
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
