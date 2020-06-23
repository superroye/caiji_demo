package com.supylc.bimo.kotlinx.view

import android.R
import android.annotation.SuppressLint
import android.content.res.ColorStateList
import android.view.View

//快速设置visiable,gone
inline fun <T : View> T.setVisibleGone(show: Boolean) {
    this.visibility = if (show) View.VISIBLE else View.GONE
}

//快速设置visiable,invisiable
inline fun <T : View> T.setVisiableOrNot(show: Boolean) {
    this.visibility = if (show) View.VISIBLE else View.INVISIBLE
}
