package com.manuflowers.notificationsfirebase.utils

import android.widget.ImageView
import androidx.annotation.DrawableRes
import com.squareup.picasso.Picasso

fun ImageView.loadUrlWithPicasso(url: String, @DrawableRes placeHolder: Int) =
    Picasso.get()
        .load(url)
        .placeholder(placeHolder)
        .error(placeHolder)
        .into(this)