package com.webin.core;

import org.keplerproject.luajava.LuaException;


public class ErrorException extends Exception {
	
	private static boolean Debug = false;
	
	public ErrorException(Exception e){
		WebinLog.E(e.toString());
	}

	public static void SAXParser(Exception e) {
		WebinLog.E(e.toString());
	}

	public static void NullPointer(Object... objs) {
		StringBuilder error = new StringBuilder();
		error.append("[NullPointer]");
		for (int i = 0; i < objs.length; i++) {
			error.append(objs + " is null;");
		}
		WebinLog.E(error.toString());
	}

	public static void Lua(LuaException e) {
		WebinLog.E(e.toString());
	}

}
