package com.webin.core.script;

import java.io.PrintWriter;

import com.webin.core.wechat.MsgTag;
import com.webin.core.wechat.TextMsg;

public class PhotoMenu implements IHandle{
	public static final String HELP_ZH = "��Ƭ";

	@Override
	public boolean isMsg(String msg) {
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
		context.append("~/��Ƭ\n");
		context.append("���Ĳ�˧,û����Ƭ.\n");
		replay.setContent(context.toString());
		writer.print(replay.toXML());
	}

}
