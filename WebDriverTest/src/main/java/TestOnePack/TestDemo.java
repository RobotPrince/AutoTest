package TestOnePack;

import java.util.ArrayList;
import java.util.List;

import com.yss.common.Common;



public class TestDemo {


	public static void main(String args[])
	{
		String[] str = AllEnum.ENUMTEST3.valueToString();
		System.out.println( AllEnum.ENUMTEST3.valueToString() );
	}
	
	//問題在第一個參數,应是enumTest，enumTest2的上层父类
	public List<String> getElementData(Class<Enum> class1,String elementString) {

		List<String> list = new ArrayList<String>();
		try{
			//这一行需要用到两种类型枚举
//			class1.valueOf(, elementString);
			Enum[] enumConstants = class1.getEnumConstants();
			enumConstants[0].toString();
		}
		catch(Exception e){
			Common.logError("ElementName not in excel");
			return null;
		}

		return list;
	}

}

interface test{
	
	enum enumTest implements test{
		A;
//		@Override
//		public toString(){
			
//		}
	}
	
	enum enumTest2 implements test{
		B
	}
}

enum enumTest3 implements test{
	C
}

enum AllEnum{
	ENUMTEST(test.enumTest.class),
	EUNMTEST2(test.enumTest2.class),
	ENUMTEST3(enumTest3.class);
	private test[] t;
	private AllEnum( Class<? extends test> e){
		 t=e.getEnumConstants();
	}
	public test[] getValue(){
		return t;
	}
	public String[] valueToString(){
		
		String[] str = new String[t.length];
		
		for(int i = 0;i < str.length; i++){
			str[i] = t[i].toString();
		}
		return str;
	}
}




