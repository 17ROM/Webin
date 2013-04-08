package com.webin.core;


public class ErrorException extends Exception {
	
	private static boolean Debug = false;
	
	public ErrorException(Exception e){
		System.out.println("[Debug]"+e);
	}

	public static void SAXParser(Exception e) {
		System.out.println("[Debug][SAXParser]"+e);
	}

	public static void NullPointer(Object... objs) {
		StringBuilder error = new StringBuilder();
		error.append("[NullPointer]");
		for (int i = 0; i < objs.length; i++) {
			error.append(objs + " is null;");
		}
		System.out.println("[Debug][SAXParser]" + error.toString());
	}

}
