package com.example.testwebmotors.utils

import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide

/**
 * Util file of project context
 *
 * @author: Marco Willy
 * @since: 18/12/2021
 **/

fun View.visible(isVisible: Boolean) {
    visibility = if (isVisible) View.VISIBLE else View.GONE
}

fun ImageView.loadImage(url: String) {
    Glide.with(this)
        .load(url)
        .into(this)
}

abstract class Constants {
    abstract class Retrofit{
        companion object {
            val BASE_URL by lazy { "https://desafioonline.webmotors.com.br/api/OnlineChallenge/" }
            val TIMEOUT_CONECTION by lazy { 60 }
        }
    }
}