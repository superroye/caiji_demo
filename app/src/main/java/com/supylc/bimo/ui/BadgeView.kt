package com.supylc.bimo.ui

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import com.supylc.bimo.R
import com.supylc.uilibs.talent.DensityUtil
import com.supylc.uilibs.talent.ResUtil

/**
 * Created by gdx on 2020/3/24 0024.
 */

class BadgeView : FrameLayout {

    private lateinit var mBadgeView: ImageView
    private lateinit var mBadgeTv: TextView

    private var isSetParams:Boolean=false

    companion object {
        const val FROM_DEFAULT = 0
        const val FROM_USER_FAMILY_INFO = 1
        //来自家族设置界面
        const val FROM_FAMILY_SETTING_PAGE=2

    }

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        initView(context)
    }

    private fun initView(context: Context) {
        val view = LayoutInflater.from(context).inflate(R.layout.common_badge_view, this, true)
        mBadgeView = view.findViewById(R.id.common_badge_img)
        mBadgeTv = view.findViewById(R.id.common_badge_tv)
        this.visibility = View.GONE

    }

    @JvmOverloads
    fun setData(badgeText: String, from: Int = FROM_DEFAULT) {
        if (badgeText.isEmpty()) {
            //家族设置页要展示没有文字的徽章
            if(from== FROM_FAMILY_SETTING_PAGE){
                this.visibility= View.VISIBLE
                setStyleParams(from)
                mBadgeTv.text = ""
            }else{
                this.visibility= View.GONE
            }
        } else {
            this@BadgeView.visibility = View.VISIBLE
            mBadgeTv.text = if (badgeText.length > 3) badgeText.substring(0, 3) else badgeText
            setStyleParams(from)
        }

    }

    private fun setStyleParams(from: Int) {
        if (isSetParams) {
            return
        }
        isSetParams = true
        when (from) {
            FROM_USER_FAMILY_INFO -> {
                createMiddleBadgeParams()
            }
            FROM_FAMILY_SETTING_PAGE -> {
                createLargeBadgeParams()
            }
            else -> {
                createDefaultBadgeParams()
            }
        }


    }

    private fun createDefaultBadgeParams() {

        val mBadgeImgParams: RelativeLayout.LayoutParams = mBadgeView.layoutParams as RelativeLayout.LayoutParams
        mBadgeImgParams.width = DensityUtil.dip2px(context, 17f)
        mBadgeImgParams.height = DensityUtil.dip2px(context, 17f)
        mBadgeView.layoutParams = mBadgeImgParams

        mBadgeTv.textSize = 10f
        mBadgeTv.setPadding(DensityUtil.dip2px(context, 11f), 0, DensityUtil.dip2px(context, 3f), 0)
        val badgeTvParams: RelativeLayout.LayoutParams = mBadgeTv.layoutParams as RelativeLayout.LayoutParams
        badgeTvParams.height = DensityUtil.dip2px(context, 14f)
        mBadgeTv.background = ResUtil.getDrawable(R.drawable.common_small_badge_bg)
        mBadgeTv.layoutParams = badgeTvParams
    }


    private fun createMiddleBadgeParams() {
        val mBadgeImgParams: RelativeLayout.LayoutParams = mBadgeView.layoutParams as RelativeLayout.LayoutParams
        mBadgeImgParams.width = DensityUtil.dip2px(context, 20f)
        mBadgeImgParams.height = DensityUtil.dip2px(context, 20f)
        mBadgeView.layoutParams = mBadgeImgParams

        mBadgeTv.textSize = 11f
        mBadgeTv.setPadding(DensityUtil.dip2px(context, 14f), 0, DensityUtil.dip2px(context, 3f), 0)
        val badgeTvParams: RelativeLayout.LayoutParams = mBadgeTv.layoutParams as RelativeLayout.LayoutParams
        badgeTvParams.height = DensityUtil.dip2px(context, 17f)
        mBadgeTv.background = ResUtil.getDrawable(R.drawable.common_middle_badge_bg)
        mBadgeTv.layoutParams = badgeTvParams
    }

    private fun createLargeBadgeParams() {
        val mBadgeImgParams: RelativeLayout.LayoutParams = mBadgeView.layoutParams as RelativeLayout.LayoutParams
        mBadgeImgParams.width = DensityUtil.dip2px(context, 25f)
        mBadgeImgParams.height = DensityUtil.dip2px(context, 25f)
        mBadgeView.layoutParams = mBadgeImgParams

        mBadgeTv.textSize = 13f
        mBadgeTv.setPadding(DensityUtil.dip2px(context, 19f), 0, DensityUtil.dip2px(context, 3f), 0)
        mBadgeTv.background = ResUtil.getDrawable(R.drawable.common_large_badge_bg)
        val badgeTvParams: RelativeLayout.LayoutParams = mBadgeTv.layoutParams as RelativeLayout.LayoutParams
        badgeTvParams.height = DensityUtil.dip2px(context, 22f)
        mBadgeTv.layoutParams = badgeTvParams
    }


}