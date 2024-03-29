package com.benguiman.lab.di

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import com.benguiman.lab.network.VolleyRequestProcessor
import com.benguiman.lab.ui.ViewModelFactory
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import javax.inject.Singleton

@Module(subcomponents = [MainActivityComponent::class], includes = [ApplicationBindModule::class])
class ApplicationModule {

    @Singleton
    @Provides
    fun provideVolleyRequestProcessor(context: Context): VolleyRequestProcessor {
        return VolleyRequestProcessor(context)
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient()
    }

    @Singleton
    @Provides
    fun provideGson(): Gson {
        return Gson()
    }

    @Singleton
    @Provides
    fun provideViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory {
        return factory
    }

}