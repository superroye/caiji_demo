package com.supylc.bimo;

import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

public class BaseToast {

    private static final String TAG = "BaseToast";

    private static Toast sToast;

    private static int sLayoutId = 0;

    private static String defaultText = "";

    public static void setLayoutId(int layoutId) {
        sLayoutId = layoutId;
    }

    public static void setDefaultText(String defaultText) {
        defaultText = defaultText;
    }

    public static void show(String text, Object... args) {
        show(String.format(text, args));
    }

    public static void show(CharSequence text) {
        show(text, Toast.LENGTH_SHORT);
    }

    public static void show(CharSequence text, int duration) {
        show(text, duration, 0, 0);
    }

    public static void show(final CharSequence text, final int duration,
                            final int xOff, final int yOff) {
        if (TextUtils.isEmpty(text)) {
            return;
        }
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                if (sToast == null) {
                    createToast();
                }

                try {
                    sToast.setGravity(Gravity.CENTER, xOff, yOff);
                    sToast.setText(text);
                    sToast.setDuration(duration);

                    SafeToastSupport.safeToastHook(sToast);

                    sToast.show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private static void createToast() {
        sToast = Toast.makeText(MyApp.app, defaultText, Toast.LENGTH_SHORT);
        if (sLayoutId != 0) {
            sToast.setView(createToastView());
        }
    }

    private static View createToastView() {
        return LayoutInflater.from(MyApp.app).inflate(sLayoutId, null);
    }

}
