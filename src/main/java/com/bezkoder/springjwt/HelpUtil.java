package com.bezkoder.springjwt;

public class HelpUtil {

	public static String ErrorFromServerToClint(String info) {
		String errorStr = "{\"ErrorFromService\":\"" + info + "\"}";
		return errorStr;
	}
	
	public static void ErrorServerLog(String info) {
		System.out.println(info);
	}
}
