package com.ullasonlineshop.data.remote

import com.ullasonlineshop.models.KartContent
import retrofit2.Call
import retrofit2.http.GET

interface MainApiService {

    @GET("bins/chou4")
    fun getContent(): Call<KartContent>
}