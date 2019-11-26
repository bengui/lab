package com.benguiman.lab

import com.benguiman.lab.di.ApplicationComponent

interface ApplicationComponentProvider {
    fun getApplicationComponent(): ApplicationComponent
}