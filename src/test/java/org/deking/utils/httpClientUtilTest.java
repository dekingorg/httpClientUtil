package org.deking.utils;
 

public class httpClientUtilTest {
	 
	public static void main(String[] args) {
		try {
			System.out.println(new httpClientUtil().get("https://www.baidu.com"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
