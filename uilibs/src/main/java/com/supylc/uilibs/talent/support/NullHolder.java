package com.supylc.uilibs.talent.support;

import android.view.View;

import com.supylc.uilibs.talent.TalentHolder;

/**
 * @author Roye
 * @date 2019/4/17
 */
@HolderRes(resName = "common_layout_recycleview_null")
public class NullHolder extends TalentHolder<NullObject> {
    public NullHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void toView(NullObject data) {

    }
}
