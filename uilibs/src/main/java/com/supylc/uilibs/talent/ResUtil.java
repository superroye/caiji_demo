package com.supylc.uilibs.talent;

import android.graphics.drawable.Drawable;

import androidx.annotation.ColorRes;
import androidx.annotation.DimenRes;
import androidx.annotation.DrawableRes;
import androidx.annotation.StringRes;

/**
 * Created by yxhuang
 * Date: 2019/4/24
 * Description:
 */
public class ResUtil {

    public static String getString(@StringRes int stringId) {
        return BaseApp.context.getResources().getString(stringId);
    }

    public static String getString(@StringRes int stringId, Object... formatArgs) {
        return BaseApp.context.getResources().getString(stringId, formatArgs);
    }

    public static int getColor(@ColorRes int colorId) {
        return BaseApp.context.getResources().getColor(colorId);
    }

    public static Drawable getDrawable(@DrawableRes int drawableId) {
        return BaseApp.context.getResources().getDrawable(drawableId);
    }

    public static float getDimension(@DimenRes int dimenId){
        return BaseApp.context.getResources().getDimension(dimenId);
    }
}
