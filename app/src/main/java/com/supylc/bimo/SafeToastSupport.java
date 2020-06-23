package com.supylc.bimo;

import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import java.lang.reflect.Field;

/**
 * Created by Supylc on 2019/8/5.
 *
 * Toast出现以下崩溃：
 * 1、has already been added to the window manager
 * 2、WindowManager$BadTokenException
 * <p>
 * hook手段针对android全版本进行try-catch
 **/
public class SafeToastSupport {

    private static final String TAG = "SafeToastSupport";
    private static Field sField_TN;
    private static Field sField_TN_Handler;
    private static boolean sIsHookFieldInit = false;
    private static final String FIELD_NAME_TN = "mTN";
    private static final String FIELD_NAME_HANDLER = "mHandler";

    private static int lastToastId;

    public static void safeToastHook(Toast toast) {
        //如果是同一个toast，就跳过了
        if (lastToastId == toast.hashCode()) {
            return;
        }

        lastToastId = toast.hashCode();

        if (!isNeedHook()) {
            return;
        }
        try {
            long c = System.currentTimeMillis();
            if (!sIsHookFieldInit) {
                sField_TN = Toast.class.getDeclaredField(FIELD_NAME_TN);
                sField_TN.setAccessible(true);
                sField_TN_Handler = sField_TN.getType().getDeclaredField(FIELD_NAME_HANDLER);
                sField_TN_Handler.setAccessible(true);
                sIsHookFieldInit = true;
            }
            Object tn = sField_TN.get(toast);
            Handler originHandler = (Handler) sField_TN_Handler.get(tn);
            sField_TN_Handler.set(tn, new SafelyHandlerWarpper(originHandler));
            Log.e(TAG, "====="+(System.currentTimeMillis()-c));
        } catch (Exception e) {
            Log.e(TAG, "Hook toast exception=" + e);
        }
    }

    private static boolean isNeedHook() {
        //针对所有版本进行try-catch
        return true;
    }

    private static class SafelyHandlerWarpper extends Handler {
        private Handler originHandler;

        public SafelyHandlerWarpper(Handler originHandler) {
            this.originHandler = originHandler;
        }

        @Override
        public void dispatchMessage(Message msg) {
            // The outside hanlder SafelyHandlerWarpper object just catches the Exception while dispatch the message
            // if the the inside system origin hanlder object throw the BadTokenException，the outside safe SafelyHandlerWarpper object
            // just catches the exception here to avoid the app crashing
            try {
                super.dispatchMessage(msg);
            } catch (Exception e) {
                Log.e(TAG, "Catch system toast exception:" + e);
            }
        }

        @Override
        public void handleMessage(Message msg) {
            //just pass the Message to the origin handler object to handle
            if (originHandler != null) {
                originHandler.handleMessage(msg);
            }
        }
    }
}
