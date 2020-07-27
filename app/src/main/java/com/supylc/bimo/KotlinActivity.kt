package com.supylc.bimo

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.supylc.kotlintest.TestCoroutine
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class KotlinActivity : AppCompatActivity() {

    var mText: TextView? = null
    var textView5: TextView? = null
    val TAG = "KotlinActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_kotlin)

        mText = findViewById(R.id.text)
        textView5 = findViewById(R.id.textView5)

        GlobalScope.launch {
            var str = TestCoroutine.test()
            Log.d(TAG, "==========str="+str)
        }

    }

    fun foo() = flow {
        for (i in 1..3) {
            println("Emitting $i")
            emit(i) // emit next value
        }
    }


    suspend fun runOnBg(): String = suspendCoroutine {
        Log.d(TAG, "=====first thread=" + Thread.currentThread().id)
        GlobalScope.launch {
            async {
                Log.d(TAG, "=====in thread=" + Thread.currentThread().id)
                it.resume("abcd")
            }
        }
    }
}
