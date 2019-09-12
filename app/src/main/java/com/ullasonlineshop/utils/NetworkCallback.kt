package com.ullasonlineshop.utils

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

fun <T> Call<T>.enqueue(callback: NetworkCallback<T>.() -> Unit) {
    val cb = NetworkCallback<T>()
    callback.invoke(cb)
    this.enqueue(cb)
}

open class NetworkCallback<T> : Callback<T> {

    var onResponse: ((Response<T>) -> Unit)? = null
    var onFailure: ((t: Throwable?) -> Unit)? = null

    override fun onFailure(call: Call<T>, t: Throwable) {
        onFailure?.invoke(t)
    }

    override fun onResponse(call: Call<T>, response: Response<T>) {
        onResponse?.invoke(response)
    }

}