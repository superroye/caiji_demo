package com.supylc.kotlintest

import android.util.Log
import java.util.concurrent.locks.ReentrantReadWriteLock
import kotlin.concurrent.read
import kotlin.concurrent.write

object TestLock {
    private val rwLock = ReentrantReadWriteLock()
    private val TAG = "TestLock"

    fun funR1() {
        rwLock.read {
            for (index in 1..10) {
                Log.d(TAG, "funR1==========${index} ${Thread.currentThread().name}")
            }
        }
    }

    fun funR2() {
        rwLock.read {
            for (index in 1..10) {
                Log.d(TAG, "funR2==========${index} ${Thread.currentThread().name}")
            }
        }
    }

    fun funR3() {
        rwLock.read {
            for (index in 1..10) {
                Log.d(TAG, "funR3==========${index} ${Thread.currentThread().name}")
            }
        }
    }

    fun funW1() {
        rwLock.write {
            for (index in 1..10) {
                Log.d(TAG, "funW1==========${index} ${Thread.currentThread().name}")
            }
        }
    }

    fun funW2() {
        rwLock.write {
            for (index in 1..10) {
                Log.d(TAG, "funW2==========${index} ${Thread.currentThread().name}")
            }
        }
    }

    fun funW3() {
        rwLock.write {
            for (index in 1..10) {
                Log.d(TAG, "funW3==========${index} ${Thread.currentThread().name}")
            }
        }
    }
}
