package com.mahaoyuan.sudoku;

public class Game {
    //储存数独的初始情况用0表示空位
    private final String[] str = new String[24];
    //表示当前关卡
    private int level;
    //对应数独的81个格子，根据玩家操作更新。
    private int[] sudoku = new int[81];
    //初始数组，不随玩家操作更新，即记录玩家不可更改的格子
    private int[] initial = new int[81];
    //表示某格子不能填哪些数
    private int[][][] used = new int[9][9][];
    //记录游戏开始时间
    private long time;

    public Game(int Level){
        level = Level;
        getString();
        sudoku = StringtoArray(str[Level-1]);
        initial=StringtoArray(str[Level-1]);
        //得到当前系统时间
        time=System.currentTimeMillis();
        calculateAllUsedTiles();
    }
    //得到游戏开始时间
    public long getTime(){return time;}
    //得到当前关卡
    public int getLevel(){return level;}
    //得到x行y列的格子中的值
    private int getTile(int x,int y){
        return sudoku[9*y+x];
    }
    //初始化24个关卡
    private void getString(){
        str[0]="360000000004230800000004200070460003820000014500013020001900000007048300000000045";
        str[1]="005020109018094003060010007690850030002100800030400051700080090500270310106040700";
        str[2]="600003007509082010070400082130007800080009030005320071950001020060870104800200003";
        str[3]="030901600050600830790004002040020050807010306010098020900500083073009060002103070";
        str[4]="480300102070460300006200700210090800007804900004003071008002400001035090603008017";
        str[5]="950400068600107004003060700040080071020905030570001090008010600100604007760002013";

        str[6]="207600001100400070003008090008000609004900500905006200070300100050001004300002708";
        str[7]="006004010020060040040080200600100302300706005204009006007050030090010050010900600";
        str[8]="007000600006832700020000080060504090100000007090703010050000070004156200008000400";
        str[9]="700108005020040010001000700600504002030070040400803006004000200070090080900307004";
        str[10]="007314900000600001000059408080000049000805000190000060201960000500001000003572100";
        str[11]="007406000030109002008000150020800060304000907060004030059000400100605080000703600";

        str[12]="700802004080600200090040600000006002050008030400000000007020080009007060100503007";
        str[13]="050800010000001700080004900500002001200130008800900007008700020005200000010009040";
        str[14]="009600000600050070005100008030200400080090050006530010100007800050080006000005300";
        str[15]="400102003008600050010005400050400800000000000001900020007500090030006100100304005";
        str[16]="200007060006504009700002040900050000004008700000000006050800004100206500080700001";
        str[17]="060200070100005020005046100070000000600300009000010040004120900030500004090007010";

        str[18]="020000900060200007700400000005700009800500006600000300000006002100005080003000040";
        str[19]="400000080060100300090800000002060900040000030008002700000006010001007050020000006";
        str[20]="020000000500007001008006002009100040200000006030070900800500200600300005000000090";
        str[21]="179000000465000000328000000000000000000000000000000000000000653000000794000000812";
        str[22]="005800700003700090009000000001004030020900070080000100000000900040002600006001800";
        str[23]="950800000104009200000340000005000090308070506020000700000058000007200603000007012";
    }
    //将x行y列的格子中的值转换为字符串
    public String getTileString(int x,int y){
        int v=getTile(x,y);
        if(v==0)
            return "";
        else
            return String.valueOf(v);
    }
    //将字符串数组转换为数字
    protected int[] StringtoArray(String str){
        int [] sudo=new int[81];
        for(int i=0;i<81;i++)
            sudo[i]=str.charAt(i)-'0';
        return sudo;
    }
    //计算x行y列的格子中不能在填的数字
    public int[] calculateUsedTile(int x,int y){
        int c[]=new int[9];
        //找出所在行已经填过的数字
        for (int i=0;i<9;i++) {
            if (i == y)
                continue;
            int t = getTile(x, i);
            if (t != 0)
                c[t - 1] = t;
        }
        //找出所在列已经填过的数字
        for (int i=0;i<9;i++) {
            if (i == x)
                continue;
            int t=getTile(i,y);
            if(t!=0)
                c[t-1]=t;
        }
        //找出所在矩形已经填过的数字
        int startx=(x/3)*3;
        int starty=(y/3)*3;
        for(int i=startx;i<startx+3;i++)
            for (int j=starty;j<starty+3;j++){
                if(i==x&&j==y)
                    continue;
                int t=getTile(i,j);
                if(t!=0)
                    c[t-1]=t;
            }

        int nused=0;
        for(int t:c)
            if(t!=0)
                nused++;
        int cc[]=new int[nused];
        nused=0;
        for(int t:c)
            if(t!=0)
                cc[nused++]=t;

        return cc;
    }
    //计算used数组
    public void calculateAllUsedTiles(){
        for(int i=0;i<9;i++)
            for (int j=0;j<9;j++)
                used[i][j]=calculateUsedTile(i,j);
    }
    //将x行y列的格子中的值改为value
    protected void setTile(int x,int y,int value){
        if(value==10)
            value=0;
        sudoku[y*9+x]=value;
    }
    //判断玩家是否可更改x行y列的格子的值
    public boolean isEditable(int x,int y){
        if(initial[x+9*y]==0)
            return true;
        else
            return false;
    }
    //判断玩家所填数字是否合法
    public boolean isValid(int x,int y){
        calculateUsedTile(x,y);
        for(int t:used[x][y])
            //若所填数字在used数组中出现过则不合法
            if(sudoku[9*y+x]==t)
                return false;
        return true;
    }
    //判断游戏是否结束
    public boolean isFinished(){
        calculateAllUsedTiles();
        for(int i=0;i<9;i++)
            for(int j=0;j<9;j++)
                if(getTile(i,j)==0||!isValid(i,j))
                    //若有格子为空，或所填数字不合法，则游戏未结束
                    return false;
        return true;
    }

}
