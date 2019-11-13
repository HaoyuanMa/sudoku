package com.mahaoyuan.sudoku;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;

public class LevelChooseActivity extends AppCompatActivity {

    private final View[] levels = new View[24];
    private float size;
    private int width,height;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //隐藏导航栏
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
        setContentView(R.layout.activity_level_choose);
        //得到屏幕尺寸
        WindowManager wm = (WindowManager) this
                .getSystemService(Context.WINDOW_SERVICE);
        width = wm.getDefaultDisplay().getWidth();
        height = wm.getDefaultDisplay().getHeight();
        //每行四个按钮
        size = width / 4.0F;
        Log.d("LevelChooseActivity", "onCreate: size:"+size);
        findViews();
        setListeners();
        setLayaout();
    }

    private void findViews() {
        levels[0] = findViewById(R.id.level_01);
        levels[1] = findViewById(R.id.level_02);
        levels[2] = findViewById(R.id.level_03);
        levels[3] = findViewById(R.id.level_04);
        levels[4] = findViewById(R.id.level_05);
        levels[5] = findViewById(R.id.level_06);
        levels[6] = findViewById(R.id.level_07);
        levels[7] = findViewById(R.id.level_08);
        levels[8] = findViewById(R.id.level_09);
        levels[9] = findViewById(R.id.level_10);
        levels[10] = findViewById(R.id.level_11);
        levels[11] = findViewById(R.id.level_12);
        levels[12] = findViewById(R.id.level_13);
        levels[13] = findViewById(R.id.level_14);
        levels[14] = findViewById(R.id.level_15);
        levels[15] = findViewById(R.id.level_16);
        levels[16] = findViewById(R.id.level_17);
        levels[17] = findViewById(R.id.level_18);
        levels[18] = findViewById(R.id.level_19);
        levels[19] = findViewById(R.id.level_20);
        levels[20] = findViewById(R.id.level_21);
        levels[21] = findViewById(R.id.level_22);
        levels[22] = findViewById(R.id.level_23);
        levels[23] = findViewById(R.id.level_24);
    }

    private void setListeners() {
        for (int i = 0; i < levels.length; i++) {
            final int t = i + 1;//表示关卡
            levels[i].setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent intent=new Intent(LevelChooseActivity.this,GameActivity.class);
                    //将关卡信息传入GameActivity
                    intent.putExtra("level",t);
                    startActivity(intent);
                }
            });
        }
    }

    private void setLayaout(){
        float white = size * 0.2F;//2margin
        float color = size * 0.8F;//按钮边长
        //获得导航栏高度
        Resources resources = this.getResources();
        int resourceId = resources.getIdentifier("navigation_bar_height","dimen", "android");
        int h = resources.getDimensionPixelSize(resourceId);
        float white_y = (height-6*color-1.5F*h)/6.0F;
        //设置Button尺寸
        TableRow.LayoutParams layoutParams = new TableRow.LayoutParams();
        layoutParams.width=(int)color;
        layoutParams.height = (int)color;
        layoutParams.leftMargin=(int)(white*0.5F);
        layoutParams.rightMargin=(int)(white*0.5F);
        layoutParams.topMargin=(int)(white_y*0.5F);
        layoutParams.bottomMargin=(int)(white_y*0.5F);
        for(int i=0;i<24;i++){
            levels[i].setLayoutParams(layoutParams);
        }
    }
}
