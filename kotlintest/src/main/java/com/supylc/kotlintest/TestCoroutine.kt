package com.supylc.kotlintest

import android.util.Log
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlin.concurrent.thread
import kotlin.coroutines.Continuation
import kotlin.coroutines.suspendCoroutine

/**
 * Created by Supylc on 2020/7/14.
 */
object TestCoroutine {

    var continuation: Continuation<String>? = null

    suspend fun test(): String {
        return suspendCoroutine {
            continuation = it
            thread(start = true) {
                Log.d("test", "==========thread")
                var ss: String? = null
                testResponse("hello")
            }
        }
    }

    fun testResponse(result: String) {
        continuation?.resumeWith(Result.success(result))
    }
}