package com.webin.core;

import java.io.PrintWriter;

import org.keplerproject.luajava.LuaException;
import org.keplerproject.luajava.LuaState;
import org.keplerproject.luajava.LuaStateFactory;

import com.webin.core.wechat.MsgTag;

public class WebinLua {
	private static WebinLua _inst = null;
	private LuaState mLuaState;
	private int error = -1;
	
	private WebinLua(){
		mLuaState = LuaStateFactory.newLuaState();
		//mLuaState.openLibs();
		//error = mLuaState.LdoFile("handle.lua");
		if (error != 0) {
			WebinLog.E("can't find lua file");
		}
	}

	public static WebinLua getInstance() {
		if (_inst == null) {
			_inst = new WebinLua();
		}
		return _inst;
	}

	public void HandleWeChat(MsgTag tag, PrintWriter writer) {
		if (error != 0){
			writer.println(WebinUtil.SYSERROR);
			return;
		}
		try {
			mLuaState.pushString(WebinUtil.getTime());
			mLuaState.pushObjectValue(tag);
			mLuaState.pushObjectValue(writer);
		} catch (LuaException e) {
			ErrorException.Lua(e);
		}
	}

}
