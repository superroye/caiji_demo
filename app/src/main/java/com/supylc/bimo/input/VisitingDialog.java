package com.supylc.bimo.input;

import android.content.Context;
import android.view.View;

public class VisitingDialog extends BottomBaseDialog {
    private View view;

    public VisitingDialog(Context context, View animateView) {
        super(context, animateView);
        this.view = animateView;
    }

    @Override
    public View onCreateView() {
        return view;
    }

    @Override
    public void setUiBeforShow() {

    }

    @Override
    public void dismiss() {
        super.dismiss();
    }
}