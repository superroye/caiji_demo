package com.supylc.bimo

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.supylc.kotlintest.CallbackDemo
import kotlinx.android.synthetic.main.activity_kotlin.*
import kotlinx.coroutines.flow.*

class KotlinActivity : AppCompatActivity() {

    var mText: TextView? = null
    var textView5: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_kotlin)

        mText = findViewById(R.id.text)
        textView5 = findViewById(R.id.textView5)

        text_View1.text = "21414134234"
        var ss: String? = null
        Log.d("aa", "aaaa=====")
        Log.d("aa", "!!!=====")


    }

    fun foo() = flow {
        for (i in 1..3) {
            println("Emitting $i")
            emit(i) // emit next value
        }
    }


    fun runOnBg(): Boolean {
        run {
            Log.d("tt", "run========" + Thread.currentThread().name + " "+CallbackDemo.testCallBack1{ a, b->
                a.toString()+"_____"+b
            } )
            return true
        }

    }
}
