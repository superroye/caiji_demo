package com.supylc.bimo.input;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.text.Layout;
import android.text.style.LeadingMarginSpan;

/**
 * Created by Supylc on 2019/9/4.
 */
//解决手动换行时的空白bug
public class FirstLineLeadingMarginSpan implements LeadingMarginSpan.LeadingMarginSpan2 {
    private final int mFirst, mRest;
    private int mNeedMargin = -1;
    private boolean mHasCallDraw;

    /**
     * Constructor taking separate indents for the first and subsequent
     * lines.
     *
     * @param first the indent for the first line of the paragraph
     * @param rest  the indent for the remaining lines of the paragraph
     */
    public FirstLineLeadingMarginSpan(int first, int rest) {
        mFirst = first;
        mRest = rest;
    }

    @Override
    public int getLeadingMargin(boolean first) {
        if (mHasCallDraw) {
            mHasCallDraw = false;
            return (mNeedMargin < 0 ? first : mNeedMargin == 1) ? mFirst : mRest;
        } else {
            return first ? mFirst : mRest;
        }
    }

    @Override
    public void drawLeadingMargin(Canvas c, Paint p, int x, int dir, int top, int baseline, int bottom, CharSequence text, int start, int end, boolean first, Layout layout) {
        mNeedMargin = start == 0 ? 1 : 0;
        mHasCallDraw = true;
    }

    @Override
    public int getLeadingMarginLineCount() {
        return 1;
    }
}