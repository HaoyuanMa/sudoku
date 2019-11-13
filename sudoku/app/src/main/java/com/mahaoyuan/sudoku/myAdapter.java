package com.mahaoyuan.sudoku;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

public class myAdapter extends SimpleCursorAdapter {
    private float width;
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = super.getView(position,convertView,parent);

        View db_date = view.findViewById(R.id.db_date);
        View db_level = view.findViewById(R.id.db_level);
        View db_time = view.findViewById(R.id.db_time);

        LinearLayout.LayoutParams linearParams0 = (LinearLayout.LayoutParams)db_date.getLayoutParams();
        linearParams0.width = (int)(width*0.6F);
        db_date.setLayoutParams(linearParams0);

        LinearLayout.LayoutParams linearParams1 = (LinearLayout.LayoutParams)db_level.getLayoutParams();
        linearParams1.width = (int)(width*0.2F);
        db_level.setLayoutParams(linearParams1);

        LinearLayout.LayoutParams linearParams2 = (LinearLayout.LayoutParams)db_time.getLayoutParams();
        linearParams2.width = (int)(width*0.2F);
        db_time.setLayoutParams(linearParams2);

        return  view;

    }

    public myAdapter(Context context, int layout, Cursor c, String[] from, int[] to) {
        super(context, layout, c, from, to);
    }

    public void setWidth(float w){
        width = w;
    }
}
