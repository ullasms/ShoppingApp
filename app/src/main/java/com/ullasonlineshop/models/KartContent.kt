package com.ullasonlineshop.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class KartContent {
    @SerializedName("code")
    @Expose
    var code: String? = null
    @SerializedName("content")
    @Expose
    var content: List<Content>? = null
}