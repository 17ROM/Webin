package com.webin.core;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

public class WebinLog {
	private static final String DEBUG = "DEBUG";
	private static final String ERROR = "ERROR";
	public static void Log(String tag, String msg) throws IOException {
		String errorlog = "webinlog.txt";
		String savePath = "";
		String logFilePath = "";
		FileWriter fw = null;
		PrintWriter pw = null;
		File file = new File(savePath);
		if (!file.exists()) {
			file.mkdirs();
		}
		logFilePath = savePath + errorlog;
		File logFile = new File(logFilePath);
		if (!logFile.exists()) {
			logFile.createNewFile();
		}
		fw = new FileWriter(logFile, true);
		pw = new PrintWriter(fw);
		pw.println("--------------------" + (new Date().toLocaleString()) + "---------------------");
		pw.println("[" + tag + "]:\n" + msg);
		pw.close();
		fw.close();
	}
	
	public static void D(String msg){
		try {
			Log(DEBUG, msg);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void E(String msg){
		try {
			Log(ERROR, msg);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
