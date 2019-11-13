package com.mahaoyuan.sudoku;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import java.util.Timer;
import java.util.TimerTask;

public class GuideActivity extends Activity {

    private static int state = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (state == 1)
            System.exit(0);
        //全屏
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
        setContentView(R.layout.activity_guide);
        //定义一个用来打开MainActivity的Intent
        Intent intent = new Intent(GuideActivity.this, MainActivity.class);
        //设置计时器，等待3s后启动活动
        Timer timer = new Timer();
        TimerTask tast = new TimerTask() {
            @Override
            public void run() {
                startActivity(intent);
            }
        };
            timer.schedule(tast, 3000);
    }

    @Override
    protected void onPause() {
        super.onPause();
        System.exit(0);
    }

    @Override
    protected void onStop() {
        super.onStop();
        state = 1;
    }

}
