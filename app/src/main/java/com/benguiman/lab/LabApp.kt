package com.benguiman.lab

import android.app.Application
import com.benguiman.lab.di.ApplicationComponent
import com.benguiman.lab.di.DaggerApplicationComponent

class LabApp : Application(), ApplicationComponentProvider {
    private lateinit var applicationComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        applicationComponent = DaggerApplicationComponent.builder()
            .context(this)
            .build()

    }

    override fun getApplicationComponent() = applicationComponent

}