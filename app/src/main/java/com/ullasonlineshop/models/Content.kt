package com.ullasonlineshop.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class Content {

    @SerializedName("sectionType")
    @Expose
    var sectionType: String? = null
    @SerializedName("name")
    @Expose
    var name: String? = null
    @SerializedName("products")
    @Expose
    var products: List<Product>? = null
    @SerializedName("bannerImage")
    @Expose
    var bannerImage: String? = null
    @SerializedName("firstImage")
    @Expose
    var firstImage: String? = null
    @SerializedName("secondImage")
    @Expose
    var secondImage: String? = null

}