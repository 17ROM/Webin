package com.webin.core;

@SuppressWarnings("serial")
public class ErrorException extends Exception {
	public ErrorException(Exception e) {
	    System.err.println(e);
	}

	public static void SAXParser(Exception e) {
		System.err.println(e);
	}

	public static void NullPointer(Object... objs) {
		StringBuilder error = new StringBuilder();
		error.append("[NullPointer]");
		for (int i = 0; i < objs.length; i++) {
			error.append(objs + " is null;");
		}
		System.err.println(error);
	}
}
