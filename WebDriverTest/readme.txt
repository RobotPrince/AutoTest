1.当前需要跑的N条测试流放在testng.xml中
2.测试流放在com.yss.suit中
3.测试流所用到的所有方法放在com.yss.method中
4.测试的log放在logs文件夹中，分为Debug级别和Error级别两级log
5.测试报告放在test-output文件夹中
6.页面中的元素放在testcase->WebElements.xls
7.页面中的元素放在testCase->TestCase.xls
8.当程序中出现包的错误时，先把工程的依赖更新一下。右键点击工程，maven->update project



/**************************************************/
程序如何跑起来？右键点击testng.xml->run as TestNG