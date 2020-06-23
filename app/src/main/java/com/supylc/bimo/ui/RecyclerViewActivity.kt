package com.supylc.bimo.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.supylc.bimo.R
import com.supylc.bimo.kotlinx.view.setVisibleGone
import com.supylc.uilibs.talent.TalentAdapter
import com.supylc.uilibs.talent.TalentHolder
import kotlinx.android.synthetic.main.activity_recycler_view.*
import kotlin.random.Random

class RecyclerViewActivity : AppCompatActivity() {

    var adapter: TalentAdapter = TalentAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_recycler_view)

        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter.registerHolderAndLayoutId(VH::class.java, R.layout.item_user_feature)
        recyclerView.adapter = adapter
        test()
    }

    fun test() {
        var list = mutableListOf<String>()
        for (i in 0..100) {
            list.add("")
        }
        adapter.resetItems(list)
    }

    class VH(itemView: View) : TalentHolder<String>(itemView) {

        lateinit var iv_family_manager: View
        lateinit var iv_nameplate: View
        lateinit var feature_badge_view: BadgeView
        lateinit var tv_wealth: View
        lateinit var tv_charm: View

        override fun initView() {
            super.initView()
            iv_family_manager = findV(R.id.iv_family_manager)
            iv_nameplate = findV(R.id.iv_nameplate)
            feature_badge_view = findV(R.id.feature_badge_view)
            tv_wealth = findV(R.id.tv_wealth)
            tv_charm = findV(R.id.tv_charm)
        }

        override fun toView(data: String) {
            iv_family_manager.setVisibleGone(Random.nextBoolean())
            iv_nameplate.setVisibleGone(Random.nextBoolean())
            feature_badge_view.setVisibleGone(Random.nextBoolean())
            feature_badge_view.setData("组长", 1)
            tv_wealth.setVisibleGone(Random.nextBoolean())
            tv_charm.setVisibleGone(Random.nextBoolean())
        }
    }
}