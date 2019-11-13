package com.mahaoyuan.sudoku;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;
import java.text.SimpleDateFormat;
import java.util.Date;


public class SudoView extends View {
    public SudoView(Context context) {
        super(context);
    }

    //记录每个格子的尺寸
    private float width;
    private float height;
    //记录触摸位置
    private int selectx;
    private int selecty;

    private Game game;

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        this.width=w/9f;
        this.height=h/9f;
        super.onSizeChanged(w, h, oldw, oldh);
    }
    //重写OnDraw()方法，绘制游戏界面
    @Override
    protected void onDraw(Canvas canvas) {

        //绘制背景颜色
        Paint background=new Paint();
        background.setARGB(175,196,224,225);
        canvas.drawRect(0,0,getWidth(),getHeight(),background);

        //格子线条画笔
        Paint light=new Paint();
        light.setARGB(100,7,152,199);
        //加深线条画笔
        Paint hilite=new Paint();
        hilite.setARGB(100,109,173,226);
        //大矩形的边界线条，更粗。
        Paint dark=new Paint();
        dark.setARGB(255,25,25,112);
        //绘制线条
        for(int i=0;i<=9;i++)
        {
            //细线
            canvas.drawLine(0,i*height,getWidth(),i*height,light);
            canvas.drawLine(0,i*height+1,getWidth(),i*height+1,light);
            canvas.drawLine(0,i*height+2,getWidth(),i*height+2,hilite);
            canvas.drawLine(i*width,0,i*width,getHeight(),light);
            canvas.drawLine(i*width+1,0,i*width+1,getHeight(),light);
            canvas.drawLine(i*width+2,0,i*width+2,getHeight(),hilite);
            //粗线
            if(i%3==0)
            {
                canvas.drawLine(i*width,0,i*width,getHeight(),dark);
                canvas.drawLine(i*width+1,0,i*width+1,getHeight(),dark);
                canvas.drawLine(i*width+2,0,i*width+2,getHeight(),hilite);
                canvas.drawLine(0,i*height,getWidth(),i*height,dark);
                canvas.drawLine(0,i*height+1,getWidth(),i*height+1,dark);
                canvas.drawLine(0,i*height+2,getWidth(),i*height+2,hilite);
            }
        }
        //单独绘制最下面的粗线
        canvas.drawLine(0,10*height,getWidth(),10*height,dark);
        canvas.drawLine(0,10*height+1,getWidth(),10*height+1,dark);
        canvas.drawLine(0,10*height+2,getWidth(),10*height+2,hilite);
        //数字画笔
        Paint number=new Paint();
        number.setStyle(Paint.Style.FILL);
        number.setTextSize(height*0.75f);
        number.setTextAlign(Paint.Align.CENTER);
        //控制数字大小
        Paint.FontMetrics fm=number.getFontMetrics();
        float x=width/2;
        float y=height/2-(fm.ascent+fm.descent)/2;
        //绘制数字
        for(int i=0;i<9;i++)
            for(int j=0;j<9;j++)
            {
                //初始化的数字，不可编辑
                if(!game.isEditable(i,j))
                    number.setColor(Color.BLACK);
                //不合法的数字
                else if(!game.isValid(i,j))
                    number.setColor(Color.RED);
                //合法数字
                else
                    number.setColor(Color.BLUE);
                canvas.drawText(game.getTileString(i,j),i*width+x,j*height+y,number);
            }
        super.onDraw(canvas);
    }
    //重写 OnTouchEvent()
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction()!=MotionEvent.ACTION_DOWN)
            return super.onTouchEvent(event);
        //得到点击位置
        selectx=(int)(event.getX()/width);
        selecty=(int)(event.getY()/height);
        //若点击位置小于零或处于不可编辑格子，不进行任何操作
        if(selecty<0||!game.isEditable(selectx,selecty))
            return false;
        //显示数字显示页面
        KeyDialog keyDialog=new KeyDialog(getContext());
        keyDialog.show();
        setListeners(keyDialog);
        return true;
    }
    //设置监听事件
    public void setListeners(final KeyDialog keyDialog){
        for(int i=0;i<keyDialog.keys.length;i++) {
            final int t = i + 1;
            keyDialog.keys[i].setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    setSelectedTile(t);
                    keyDialog.dismiss();
                    //如果游戏结束，调用Finish()方法
                    if(game.isFinished())
                        Finish();
                }
            });
        }
    }
    //填入数字，更新game
    public void setSelectedTile(int tile){
        game.setTile(selectx,selecty,tile);
        game.calculateAllUsedTiles();
        this.invalidate();
        }
    //设置游戏关卡
    public void setGame(int level) {
        game=new Game(level);
    }
    //结束游戏
    private void Finish(){
        //计算游戏时间
        long t = (System.currentTimeMillis()-game.getTime())/1000;
        //弹出结束对话框
        FinishDialog finishDialog=new FinishDialog(getContext());
        finishDialog.setTime(t);
        finishDialog.show();
        //将本局游戏信息写入数据库
        MySQLiteOpenHelper mySQLiteOpenHelper = new MySQLiteOpenHelper(getContext());
        SQLiteDatabase mydatebase = mySQLiteOpenHelper.getWritableDatabase();
        ContentValues record = new ContentValues();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        record.put("date",simpleDateFormat.format(date));
        record.put("level",game.getLevel());
        record.put("time",t);
        mydatebase.insert("Rank",null,record);
    }
}
