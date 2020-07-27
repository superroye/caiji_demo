package com.supylc.kotlintest

import kotlinx.coroutines.*
import kotlin.coroutines.suspendCoroutine

/**
 * Created by Supylc on 2020/7/6.
 */
class TestDecomplie {

    fun test() {
        GlobalScope.launch {

            async(Dispatchers.IO) {
                test1()
                test2()
                test3()
            }
            test4()
        }

        var job1 = GlobalScope.launch(Dispatchers.IO, CoroutineStart.LAZY) {
            //doSomething
        }

        GlobalScope.async (Dispatchers.IO) {
            var ss = coroutineScope {

            }

        }

        runBlocking {

        }

    }

    suspend fun test1():String {
        suspendCoroutine<String> {  }
        return "test1"
    }

    suspend fun test2():String {
        return "test2"
    }

    suspend fun test3():String {
        return "test3"
    }

    suspend fun test4():String {
        return "test4"
    }

    suspend fun test5():String {
        return "test5"
    }

}