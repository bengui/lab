package com.benguiman.lab.ui.third_screen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.benguiman.lab.di.ActivityScope
import javax.inject.Inject
import javax.inject.Provider

@ActivityScope
class ViewModelFactory @Inject constructor(private val viewModels: MutableMap<Class<out ViewModel>, Provider<ViewModel>>) :
    ViewModelProvider.Factory {

    init {
        Log.d(
            "LabApp", "init"
        )
    }

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        val viewModel = viewModels[modelClass]?.get()
        Log.d(
            "LabApp",
            "Create new instance of ${modelClass.simpleName} Instance: ${viewModel.hashCode()}"
        )
        return viewModel as T
    }
}
