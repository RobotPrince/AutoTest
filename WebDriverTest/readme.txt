/***************************�ļ�·��˵��**************************/

1.��ǰ��Ҫ�ڱ����ܵ�N������������testng.xml��
Ex��(scenario1[��¼->���������Ϣ����->���������Ϣ���]��scenario2[��¼->��Ʒ����->��Ʒ���])

2.���������ڰ�com.yss.suit��
Ex��(scenario1��scenario2)

3.���������õ������з������ڰ�com.yss.method��
Ex:(Login,HeSuanJiGouXinXi,GuanLianJiGouXinXi,ChanPinXinXi)

4.���Ե�log����logs�ļ����У���ΪDebug�����Error��������log
PS:(���ļ�����Ϊ����������ϸlog)

5.���Ա������test-output�ļ�����
PS:(./html�д��reportNG�����ĳɹ��ﱨ�棬./Screen-shot�д�ŵ���ʧ�ܽ�ͼ��/ΪTestNGԭ���Ĳ��Ա���)

6.ҳ���е�Ԫ�ط���testcase->WebElements.xls
Ex:(����Loginҳ������Ҫ�õ���Ԫ�صĶ�λ����login_page,user,xpath,.//*[@id="username"],W	,�û�)

7.ҳ��������Ҫ�õ���Ԫ�ط���testCase->TestCase.xls
Ex:(����Loginҳ��������Ҫ�õ������ݣ���user,tll)

/******************������������**********************/
!!!!!!!�Ⱦ�������װJDk1.7(1.6�汾���ͣ�1.7��ʱδ��������)��Eclipse-kepler��Maven��git��TestNG(Eclipse���)!!!!!!!!

1.��������Ļ������� maven jdk
2.�������г��ְ��Ĵ���ʱ���Ȱѹ��̵���������һ�¡��Ҽ�������̣�maven->update project
3.���Ա������������룬��/reportng-1.1.5.jar����%M2_HOME%/repository/org/uncommons/reportng/1.1.2,���Ҹ���Ϊreportng-1.1.2.jar����ԭ����

����������������Ҽ����testng.xml->run as TestNG