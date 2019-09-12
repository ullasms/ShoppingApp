package com.ullasonlineshop.di.main

import com.ullasonlineshop.data.remote.MainApiService
import com.ullasonlineshop.data.repository.KartRepository
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class MainModule {

    @MainScope
    @Provides
    fun provideMainApiService(retrofitBuilder: Retrofit.Builder): MainApiService {
        return retrofitBuilder
            .build()
            .create(MainApiService::class.java)
    }

    @MainScope
    @Provides
    fun provideCreateBlogRepository(
        mainApiService: MainApiService
    ): KartRepository {
        return KartRepository(mainApiService)
    }
}