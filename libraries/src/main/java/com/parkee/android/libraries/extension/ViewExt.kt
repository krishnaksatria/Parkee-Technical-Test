package com.parkee.android.libraries.extension

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes

fun View.setGone() {
    if (!isGone())
        this.visibility = View.GONE
}

fun View.setVisible() {
    if (!isVisible())
        this.visibility = View.VISIBLE
}

fun View.isVisible(): Boolean = visibility == View.VISIBLE
fun View.isGone(): Boolean = visibility == View.GONE

fun ViewGroup.inflate(@LayoutRes layoutRes: Int): View =
    LayoutInflater.from(context).inflate(layoutRes, this, false)
