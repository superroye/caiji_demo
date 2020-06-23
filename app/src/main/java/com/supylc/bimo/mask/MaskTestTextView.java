package com.supylc.bimo.mask;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import androidx.appcompat.widget.AppCompatEditText;

public class MaskTestTextView extends AppCompatEditText {

    private Paint mMaskPaint;
    private final int HEIGHT_MASK = 86;


    public MaskTestTextView(Context context, AttributeSet attrs) {
        super(context, attrs);

        setLayerType(View.LAYER_TYPE_SOFTWARE, null);

        mMaskPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mMaskPaint.setColor(0x00000000);
        mMaskPaint.setShadowLayer(100, 0,20, 0xffffffff);
        append("fsagaggagdgdafdsgfadhghdfahb\n");
        append("fsagaggagdgdafdsgfadhghdfahb\n");
        append("fsagaggagdgdafdsgfadhghdfahb\n");
        append("fsagaggagdgdafdsgfadhghdfahb\n");
        append("fsagaggagdgdafdsgfadhghdfahb\n");
        append("fsagaggagdgdafdsgfadhghdfahb\n");
        append("fsagaggagdgdafdsgfadhghdfahb\n");
        append("fsagaggagdgdafdsgfadhghdfahb\n");
        append("fsagaggagdgdafdsgfadhghdfahb\n");
        append("fsagaggagdgdafdsgfadhghdfahb\n");
        append("fsagaggagdgdafdsgfadhghdfahb\n");
        append("fsagaggagdgdafdsgfadhghdfahb\n");
        append("fsagaggagdgdafdsgfadhghdfahb\n");
        append("fsagaggagdgdafdsgfadhghdfahb\n");
        append("fsagaggagdgdafdsgfadhghdfahb\n");
        append("fsagaggagdgdafdsgfadhghdfahb\n");
    }

    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        paint.setMaskFilter(new BlurMaskFilter(30, BlurMaskFilter.Blur.NORMAL));
//        canvas.drawRect(100, 100, 250, 250, paint);
//
//        paint.setMaskFilter(new BlurMaskFilter(30, BlurMaskFilter.Blur.SOLID));
//        canvas.drawRect(100, 350, 250, 500, paint);
//
//        paint.setMaskFilter(new BlurMaskFilter(30, BlurMaskFilter.Blur.OUTER));
//        canvas.drawRect(100, 600, 250, 750, paint);
        //canvas.drawRect(getLeft(), getTop(), getRight(), getTop() + maskHeight, mMaskPaint);
        //canvas.drawRect(getLeft(), getBottom() - maskHeight, getRight(), getBottom(), mMaskPaint);

        canvas.drawRect(0, 0+HEIGHT_MASK, getWidth(), getHeight()-HEIGHT_MASK, mMaskPaint);
    }
}
