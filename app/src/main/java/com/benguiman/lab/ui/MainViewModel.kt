package com.benguiman.lab.ui

import androidx.lifecycle.ViewModel
import javax.inject.Inject

class MainViewModel @Inject constructor() : ViewModel() {
    var currentScreen = CurrentScreen.MAIN_MENU
}