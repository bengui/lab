package com.benguiman.lab.ui.menu_screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.benguiman.lab.R
import com.benguiman.lab.ui.MainActivityComponentProvider
import com.benguiman.lab.ui.Navigator
import javax.inject.Inject

class MenuFragment : Fragment() {

    @Inject
    lateinit var navigator: Navigator

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_menu, container, false)
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        (context as MainActivityComponentProvider).getMainActivityComponent()
            .inject(this)

        view?.findViewById<Button>(R.id.first_screen_btn)?.setOnClickListener {
            navigator.navigateToFirstScreen()
        }
        view?.findViewById<Button>(R.id.second_screen_btn)?.setOnClickListener {
            navigator.navigateToSecondScreen()
        }
        view?.findViewById<Button>(R.id.third_screen_btn)?.setOnClickListener {
            navigator.navigateToThirdScreen()
        }
    }


}