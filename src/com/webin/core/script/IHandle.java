package com.webin.core.script;

import java.io.PrintWriter;

import com.webin.core.wechat.MsgTag;

public interface IHandle {
	boolean isMsg(String msg);
	void handleMsg(MsgTag tag, PrintWriter writer);
}
