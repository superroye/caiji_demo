package com.supylc.kotlintest

import android.util.Log
import org.jetbrains.annotations.TestOnly

/**
 * Created by Supylc on 2020/7/13.
 */
object TestRegx {

    fun test() {
        val regex = Regex("((http|ftp|https):\\/\\/)?[\\w\\-_]+(\\.[\\w\\-_]+)+([\\w\\-\\.,@?^=%&amp;:/~\\+#]*[\\w\\-\\@?^=%&amp;/~\\+#])?")
        val input = "https://www.google.com.hk/ https://www.google.com.hk/sss 放水https://www.google.com.hk/ 放水说"
        regex.findAll(input)?.forEach {
            Log.d("TestRegx", "=======it.range=(${it.range}) ${it.value}")
        }
    }


}