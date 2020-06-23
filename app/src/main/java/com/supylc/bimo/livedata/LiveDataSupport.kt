package com.supylc.bimo.livedata

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

fun <T> LiveData<T>.observeEvent(owner: LifecycleOwner, eventObserver: (T) -> Unit) {
    observe(owner, object : Observer<T> {
        var mEventFlag = 0
        override fun onChanged(value: T?) {
            if (value != null && mEventFlag != value.hashCode()) {
                mEventFlag = value.hashCode()
                eventObserver.invoke(value)
            }
        }
    })
}