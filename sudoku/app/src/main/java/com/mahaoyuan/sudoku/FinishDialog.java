package com.mahaoyuan.sudoku;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;

public class FinishDialog extends Dialog {
    public FinishDialog(Context context) {
        super(context);
    }

    private long Time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.finishdialog);
        TextView textView=findViewById(R.id.info3);
        textView.append(String.valueOf(Time)+"s.");
    }

    public void setTime(long time){
        Time=time;
    }
}
