package com.ullasonlineshop.data.repository

import com.ullasonlineshop.data.remote.MainApiService
import com.ullasonlineshop.models.KartContent
import com.ullasonlineshop.utils.NetworkCallback
import retrofit2.Call
import retrofit2.Response
import javax.inject.Inject

class KartRepository @Inject constructor(val mainApiService: MainApiService) {

    fun getContent(success: (KartContent) -> Unit, failure: (String) -> Unit) {
        mainApiService.getContent().enqueue(object : NetworkCallback<KartContent>() {

            override fun onResponse(call: Call<KartContent>, response: Response<KartContent>) {
                super.onResponse(call, response)
                response.body()?.let {
                    success(it)
                } ?: response.errorBody()?.let {
                    failure(it.toString())
                }
            }

            override fun onFailure(call: Call<KartContent>, t: Throwable) {
                super.onFailure(call, t)
                t.message?.let {
                    failure(it)
                }
            }
        })
    }
}