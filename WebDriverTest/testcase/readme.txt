/****************说明******************/


/***************WebElements.xls(简称W)-说明***********************/

1.页面中的元素放在testcase->WebElements.xls
Ex:(定义Login页面所有要用到的元素的定位，如login_page,user,xpath,.//*[@id="username"],W	,用户)

2.严格按照顺序page_name，element_name，type，value，remark来写各个元素

3.page_name:元素所在的页面的名称，对应枚举定义在com.yss.common.PageEnum中

4.element_name: 元素名称，对应枚举定义在各个所属的method中
Ex:(核算机构信息页面对应的所有元素定义在com.yss.method.HeSuanJiGouXinXi.HeSuanJiGouXinXiEnum）

5.type:元素定位方式，对应的枚举定义在com.yss.common.LocatorTypeEnum

6.value:元素定位值

7.remark:元素的动作，对应枚举定义在com.yss.com.RemarkEnum

/***************TestCase.xls(简称T)-说明***********************/

1.页面中所需要用到的元素放在testCase->TestCase.xls
Ex:(定义Login页面所有需要用到的数据，如user,tll)

2.sheet名称为W中的page_name

3.各列为元素的名称对应于W中的element_name

4.若该列是select元素，则值也应为W中想要选择的element_name
Ex:(JiGuZhuangTai是select，想在下拉选择zhuxiao。其中JiGouZhuangTai和 ZhuXiao都是W的element_name中定义好的)

5.末尾列为IsChecked，用来标志该条数据是否需要审核

6.每一行为需要新增的一条数据