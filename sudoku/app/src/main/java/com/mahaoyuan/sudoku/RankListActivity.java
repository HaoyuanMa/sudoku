package com.mahaoyuan.sudoku;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.TableRow;
import android.widget.TextView;


public class RankListActivity extends AppCompatActivity {

    private float width;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rank_list);

        setLayout();

        MySQLiteOpenHelper mySQLiteOpenHelper = new MySQLiteOpenHelper(this);
        SQLiteDatabase db = mySQLiteOpenHelper.getReadableDatabase();
        String sql = "SELECT date as _id, level, time FROM Rank ORDER BY level, time;";
        Cursor cursor = db.rawQuery(sql,null);
        myAdapter adapter =
                new myAdapter(this,R.layout.item,cursor,
                    new String[]{"_id","level","time"},
                        new int[]{R.id.db_date,R.id.db_level,R.id.db_time});
        adapter.setWidth(width);
        ListView listview = findViewById(R.id.ranklist);
        listview.setAdapter(adapter);
    }

    private void setLayout(){
        //得到屏幕尺寸
        WindowManager wm = (WindowManager) this
                .getSystemService(Context.WINDOW_SERVICE);
        width = wm.getDefaultDisplay().getWidth();

        TableRow.LayoutParams layoutParams_date = new TableRow.LayoutParams();
        layoutParams_date.width=(int)(width*0.6F);
        layoutParams_date.rightMargin=3;
        layoutParams_date.leftMargin=3;
        layoutParams_date.topMargin=3;
        layoutParams_date.bottomMargin=3;
        TextView tv_date = findViewById(R.id.date);
        tv_date.setLayoutParams(layoutParams_date);

        TableRow.LayoutParams layoutParams_level = new TableRow.LayoutParams();
        layoutParams_level.width=(int)(width*0.2F);
        layoutParams_level.rightMargin=3;
        layoutParams_level.leftMargin=3;
        TextView tv_level = findViewById(R.id.level);
        tv_level.setLayoutParams(layoutParams_level);

        TableRow.LayoutParams layoutParams_time = new TableRow.LayoutParams();
        layoutParams_time.width=(int)(width*0.2F);
        layoutParams_time.leftMargin=3;
        layoutParams_time.rightMargin=3;
        TextView tv_time = findViewById(R.id.usetime);
        tv_time.setLayoutParams(layoutParams_time);

    }

}

