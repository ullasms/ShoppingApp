package com.ullasonlineshop.di.main

import androidx.lifecycle.ViewModel
import com.ullasonlineshop.di.ViewModelKey
import com.ullasonlineshop.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class MainViewModelsModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindMainViewModel(viewModel: MainViewModel): ViewModel
}