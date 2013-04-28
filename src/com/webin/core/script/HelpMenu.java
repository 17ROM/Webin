package com.webin.core.script;

import java.io.PrintWriter;

import com.webin.core.wechat.MsgTag;
import com.webin.core.wechat.TextMsg;

public class HelpMenu implements IHandle{
	
	public static final String HELP_ZH = "帮助|聊天|收录";

	public static boolean isMsg(String msg) {
		if (HELP_ZH.contains(msg)){
			return true;
		}
		return false;
	}
	
	private void menuNormal(MsgTag tag, PrintWriter writer, String msg) {
		TextMsg replay = new TextMsg(tag.getMsg());
		replay.setContent(msg);
		writer.print(replay.toXML());
	}
	
	private void menuChat(MsgTag tag, PrintWriter writer){
		String content = "随便聊点什么吧";
		menuNormal(tag, writer, content);
	}
	
	private void menuRecorder(MsgTag tag, PrintWriter writer){
		String content = "回复 收录,关键字,回复  即可收录你想要的回复.\n 例子: 收录,我喜欢你,我也喜欢你";
		menuNormal(tag, writer, content);
	}
	
	private void handleHelp(MsgTag tag, PrintWriter writer){
		TextMsg replay = new TextMsg(tag.getMsg());
		StringBuilder context = new StringBuilder();
		context.append("Hi~\n");
		context.append("现已开通功能:\n");
		context.append("1、收录\n");
		context.append("2、聊天\n");
		context.append("回复文本即可查看.如果你不想谈这些，那随便聊点什么吧.");
		replay.setContent(context.toString());
		writer.print(replay.toXML());
	}

	@Override
	public void handleMsg(MsgTag tag, PrintWriter writer) {
		if (tag.Content.equals("帮助")){
			handleHelp(tag, writer);
		}else if (tag.Content.equals("聊天")){
			menuChat(tag, writer);
		}else if (tag.Content.equals("收录")){
			menuRecorder(tag, writer);
		}
	}

}
