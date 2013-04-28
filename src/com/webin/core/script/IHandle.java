package com.webin.core.script;

import java.io.PrintWriter;

import com.webin.core.wechat.MsgTag;

public interface IHandle {
	void handleMsg(MsgTag tag, PrintWriter writer);
}
