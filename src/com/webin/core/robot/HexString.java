package com.webin.core.robot;

public class HexString {
	public static String StringtoHex(String chs){
		StringBuilder builder = new StringBuilder();
		for (char ch : chs.toCharArray()){
			String hex = Integer.toHexString(ch);
			builder.append(hex);
		}
		return builder.toString();
	}
}
