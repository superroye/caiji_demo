package com.supylc.bimo;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.supylc.bimo.input.VisitingDialog;

public class InputTestActivity extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_input_test);
        findViewById(R.id.textView3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(InputTestActivity.this, KotlinActivity.class);
//                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
//                startActivity(intent);

                Intent intent = new Intent();
                intent.setData(Uri.parse("http://www.baidu.com"));//Url 就是你要打开的网址
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
                intent.setAction(Intent.ACTION_VIEW);
                startActivity(intent); //启动浏览器
            }
        });


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        Log.d("111", "InputTestActivity onDestroy");
    }
}
