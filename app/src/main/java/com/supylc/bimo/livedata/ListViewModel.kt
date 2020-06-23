package com.supylc.bimo.livedata

import android.util.Log
import androidx.lifecycle.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class ListViewModel : ViewModel(), LifecycleObserver {

    val TAG = "ListViewModel"

    //列表
    var list = MutableLiveData<ArrayList<String>>()
        private set

    //分页类
    var pageEntity = MutableLiveData<Pair<Int, Int>>()
        private set

    var toast = MutableLiveData<String>()
        private set

    var page: Int = 0

    //异步加载数据，并postValue
    fun loadData() {
        GlobalScope.launch {
            var defered = async {
                var dataList = ArrayList<String>()
                for (i in 0 until 10) {
                    dataList.add("I am Item! page=$page item=$i")
                }

                page++

                var oList = list.value
                var refreshStart = oList?.size ?: 0
                if (oList == null) {
                    oList = ArrayList()
                }
                oList.addAll(dataList)
                if (refreshStart == 0) {
                    Log.d(TAG, "setValue, list")
                    list.postValue(oList)
                }

                var refreshRange = oList.size - refreshStart
                if (refreshStart > 0) {
                    Log.d(TAG, "setValue, pageEntity")
                    pageEntity.postValue(Pair(refreshStart, refreshRange))
                }
                refreshRange
            }
            var size = defered.await()

            Log.d(TAG, "setValue, toast")
            toast.postValue("加载了$size 条数据")
        }
    }

    /******************生命周期感知****************/
    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun onResume(lifecycleOwner: LifecycleOwner) {
        Log.d(TAG, "onResume")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun onStop(lifecycleOwner: LifecycleOwner) {
        Log.d(TAG, "onStop")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun onPause(lifecycleOwner: LifecycleOwner) {
        Log.d(TAG, "onPause")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun onDestroy(lifecycleOwner: LifecycleOwner) {
        Log.d(TAG, "onDestroy")
    }
}