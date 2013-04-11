package com.webin.core;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.io.Reader;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class WebinScript {
	private static WebinScript _inst = null;
	private String mHandleStartTime;
	private String mHandleEndTime;
	private ScriptEngineManager mManager;
	private ScriptEngine mEngine;
	
	private WebinScript(){
		mManager = new ScriptEngineManager();
		mEngine = mManager.getEngineByName("javascript");
		runJavaScript();
	}
	
	private void runJavaScript(){
		try {
			Reader reader = new FileReader(WebinConfig.JSFILE);
			mEngine.eval(reader);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (ScriptException e) {
			e.printStackTrace();
		}
	}
	
	private Object getHandleWeChat(String msg){
		Invocable func = (Invocable) mEngine;
		Object result = null;
		try {
			result = func.invokeFunction("handleWeChat", msg);
		} catch (ScriptException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public static WebinScript getInstance() {
		if (_inst == null) {
			_inst = new WebinScript();
		}
		return _inst;
	}

	public void HandleWeChat(String msg, PrintWriter writer) {
		// record start time
		mHandleStartTime = WebinUtil.getTime();
		// script handle
		Object replay = getHandleWeChat(msg);
		// record end time
		mHandleEndTime = WebinUtil.getTime();
		if (!WebinUtil.isTimeOut(mHandleStartTime, mHandleEndTime)) {
			if (replay != null) {
				writer.print(replay);
			}
		}
	}
}
