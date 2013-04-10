package com.webin.core;

import java.util.Date;

public class WebinUtil {
	public static final String SYSERROR = "[System sleeping]";
	public static final String TIMEOUT = "[Time out]";

	public static String getTime(){
		return String.valueOf(new Long(new Date().getTime()));
	}
	
	public static boolean isTimeOut(String pretime, String newtime) {
		long time1 = Long.valueOf(pretime);
		long time2 = Long.valueOf(newtime);
		return (time2 - time1 > 5000);
	}
}
