package com.yss.suit;

import com.yss.method.Login;

public class suit {
	/*
	 * 将这条测试流所用到的方法写在这里
	 * 如：1.登陆 2.开户 3.申购
	 */
	public boolean runSuit() {
		return new Login().login();
	}
}
