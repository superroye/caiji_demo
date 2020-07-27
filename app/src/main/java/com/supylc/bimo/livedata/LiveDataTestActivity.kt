package com.supylc.bimo.livedata

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.supylc.bimo.R
import com.supylc.bimo.kotlinx.click.clickLimit
import com.supylc.uilibs.talent.TalentAdapter
import com.supylc.uilibs.talent.TalentHolder
import kotlinx.android.synthetic.main.activity_live_data.*

class LiveDataTestActivity : AppCompatActivity() {

    val TAG = "LiveDataTestActivity"

    val listViewModel: ListViewModel by lazy {
        ViewModelProviders.of(this@LiveDataTestActivity).get(ListViewModel::class.java)
    }

    var talentAdapter: TalentAdapter = TalentAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //下面设置listViewModel感知Activity(Fragment)的生命周期
        lifecycle.addObserver(listViewModel)

        setContentView(R.layout.activity_live_data)

        initView()
        setListener()
        //设置liveData的观察者
        setObserves()
    }

    fun initView() {
        rvList.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        talentAdapter.registerHolderAndLayoutId(ItemVH::class.java, R.layout.layout_item_text)
        rvList.adapter = talentAdapter
    }

    fun setListener() {
        tvPageTest.clickLimit {
            listViewModel.loadData()
        }
    }

    fun setObserves() {
        Log.d(TAG, "setObserves")
        listViewModel.list.observe(this, Observer {
            Log.d(TAG, "setObserves, list")
            talentAdapter.resetItems(it)
        })

        listViewModel.pageEntity.observe(this, Observer {
            Log.d(TAG, "setObserves, pageEntity")
            talentAdapter.notifyItemRangeChanged(it.first, it.second)
        })

        listViewModel.toast.observeEvent(this, {
            Log.d(TAG, "setObserves, toast")
            Toast.makeText(this@LiveDataTestActivity, it, Toast.LENGTH_SHORT).show()
        })
    }

    override fun onDestroy() {
        super.onDestroy()

        //不必释放观察者
    }

    class ItemVH(itemView: View) : TalentHolder<String>(itemView) {

        var text: TextView? = null
        override fun initView() {
            text = findV(R.id.text)
        }

        override fun toView(data: String?) {
            text?.text = data
        }
    }
}