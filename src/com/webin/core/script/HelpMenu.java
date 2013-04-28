package com.webin.core.script;

import java.io.PrintWriter;

import com.webin.core.wechat.MsgTag;
import com.webin.core.wechat.TextMsg;

public class HelpMenu implements IHandle{
	
	public static final String HELP_ZH = "帮助";

	public static boolean isMsg(String msg) {
		if (HELP_ZH.equals(msg)){
			return true;
		}
		return false;
	}

	@Override
	public void handleMsg(MsgTag tag, PrintWriter writer) {
		TextMsg replay = new TextMsg(tag.getMsg());
		StringBuilder context = new StringBuilder();
		context.append("Hi~\n");
		context.append("现已开通功能:\n");
		context.append("1、信息\n");
		context.append("2、聊天\n");
		context.append("回复文本即可查看.如果你不想谈这些，那随便聊点什么吧.");
		replay.setContent(context.toString());
		writer.print(replay.toXML());
	}

}
