package com.ullasonlineshop.di

import com.ullasonlineshop.di.main.MainModule
import com.ullasonlineshop.di.main.MainScope
import com.ullasonlineshop.di.main.MainViewModelsModule
import com.ullasonlineshop.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuildersModule {

    @MainScope
    @ContributesAndroidInjector(
        modules = arrayOf(MainViewModelsModule::class, MainModule::class)
    )
    abstract fun contributeMainActivity(): MainActivity
}