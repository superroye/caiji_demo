package com.supylc.kotlintest

class CallbackDemo {


    /**
     *
     * 定义和调用
     * testCallBack{a,b->
    a.toString()+"_____"+b
    }
     *
     */
    fun testCallBack(callback: (ii: Int, ss: String) -> String): String {
        return callback(111, "2222")
    }

    companion object StaticParams {
        fun testCallBack1(callback: (ii: Int, ss: String) -> String): String {
            return callback(111, "2222")
        }
    }
}