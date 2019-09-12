package com.ullasonlineshop.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class Product {

    @SerializedName("name")
    @Expose
    var name: String? = null
    @SerializedName("price")
    @Expose
    var price: String? = null
    @SerializedName("imageURL")
    @Expose
    var imageURL: String? = null
    @SerializedName("type")
    @Expose
    var type: String? = null

}