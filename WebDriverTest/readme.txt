/***************************文件路径说明**************************/

1.当前需要在本次跑的N条测试流放在testng.xml中
Ex：(scenario1[登录->核算机构信息新增->核算机构信息审核]，scenario2[登录->产品新增->产品审核])

2.测试流放在包com.yss.suit中
Ex：(scenario1，scenario2)

3.测试流所用到的所有方法放在包com.yss.method中
Ex:(Login,HeSuanJiGouXinXi,GuanLianJiGouXinXi,ChanPinXinXi)

4.测试的log放在logs文件夹中，分为Debug级别和Error级别两级log
PS:(该文件夹下为调试所用详细log)

5.测试报告放在test-output文件夹中
PS:(./html中存放reportNG产生的成果物报告，./Screen-shot中存放的是失败截图，/为TestNG原生的测试报告)

6.页面中的元素放在testcase->WebElements.xls
Ex:(定义Login页面所有要用到的元素的定位，如login_page,user,xpath,.//*[@id="username"],W	,用户)

7.页面中所需要用到的元素放在testCase->TestCase.xls
Ex:(定义Login页面所有需要用到的数据，如user,tll)

/******************常见部署问题**********************/
!!!!!!!先决条件安装JDk1.7(1.6版本过低，1.7暂时未发现问题)，Eclipse-kepler，Maven，git，TestNG(Eclipse插件)!!!!!!!!

1.上述软件的环境变量 maven jdk
2.当程序中出现包的错误时，先把工程的依赖更新一下。右键点击工程，maven->update project
3.测试报告中中文乱码，将/reportng-1.1.5.jar移至%M2_HOME%/repository/org/uncommons/reportng/1.1.2,并且更名为reportng-1.1.2.jar覆盖原来的

程序如何跑起来？右键点击testng.xml->run as TestNG