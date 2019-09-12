package com.ullasonlineshop.utils

import android.widget.ImageView
import com.bumptech.glide.RequestManager

fun ImageView.loadImage(requestManager: RequestManager, url: String?) = requestManager.load(url).into(this)