package com.supylc.bimo.kotlinx.click

import android.view.View
import com.supylc.bimo.R

/**
 * Created by Supylc on 2019/10/22.
 */

fun <T : View> T.clickLimit(block: (T) -> Unit) = setOnClickListener {
    if (canClick(it, 800)) {
        block(it as T)
    }
}

fun <T : View> T.clickNoLimit(block: (T) -> Unit) = setOnClickListener {
    block(it as T)
}

fun <T : View> T.clickLimitShort(block: (T) -> Unit) = setOnClickListener {
    if (canClick(it, 500)) {
        block(it as T)
    }
}

fun <T : View> T.clickLimitToast(block: (T) -> Unit, intervalTime: Long) = setOnClickListener {
    if (canClick(it, intervalTime)) {
        block(it as T)
    } else {
    }
}

private fun canClick(view: View, intervalTime: Long): Boolean {
    val key = R.id.key_click_limit
    val currentTime = System.currentTimeMillis()
    var tag = view.getTag(key)
    var lastClickTime = if (tag === null) 0 else tag as Long

    val realIntervalTime = currentTime - lastClickTime
    view.setTag(key, currentTime)

    return realIntervalTime >= intervalTime
}
