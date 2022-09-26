
## **4.Android端数独小游戏（Android，Java）**
Android开发课程作业
###  4.1 简介
一个Android平台的数独小游戏，共24到数独题目，游戏界面对于玩家填入的正确数字以蓝色字体显示，若存在冲突，以红色字体提醒。完成数独后，计算用时以提供一个简单的排行榜。
###  4.2 技术细节
- 语言框架：
    - Java，Android，SQLite
- 技术细节
    - 设计引导页面，App启动后显示封面图三秒后跳转至主页面。
    - 设计SudoView（继承自View）绘制游戏页面，并实现对应的填数逻辑。设计KeyDialog（继承自Dialog）绘制玩家填数时显示的输入pad，同时实现相关逻辑。
    - 设计Game类封装游戏逻辑，使之与视图显示及用户交互逻辑上解耦。
    - 使用SQLite存储玩家解题用时。

###  4.3 功能演示
- 引导界面/主页面：<br>
![主页面][pic-4.3.0]<br>
- 游戏页面：<br>
![游戏页面][pic-4.3.1]<br>
- 完成游戏/排行榜：<br>
![排行榜][pic-4.3.2]<br>

###  4.4 项目仓库
<https://github.com/HaoyuanMa/sudoku>

----

[pic-4.3.0]: https://images.wait4echo.love/Works/sudoku/wl-sudoku-main.png
[pic-4.3.1]: https://images.wait4echo.love/Works/sudoku/wl-sudoku-game.png
[pic-4.3.2]: https://images.wait4echo.love/Works/sudoku/wl-sudoku-rank.png

