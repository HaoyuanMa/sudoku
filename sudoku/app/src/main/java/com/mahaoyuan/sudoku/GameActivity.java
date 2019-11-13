package com.mahaoyuan.sudoku;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class GameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent=getIntent();
        final int level=intent.getIntExtra("level",1);
        SudoView sudoView=new SudoView(this);
        sudoView.setGame(level);
        setContentView(sudoView);
        setTitle("第"+level+"关");
    }
}
