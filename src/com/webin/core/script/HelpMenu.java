package com.webin.core.script;

import java.io.PrintWriter;

import com.webin.core.wechat.MsgTag;
import com.webin.core.wechat.TextMsg;

public class HelpMenu implements IHandle{
	
	public static final String HELP_ZH = "����";

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
		context.append("���ѿ�ͨ����:\n");
		context.append("1����Ϣ\n");
		context.append("2������\n");
		context.append("�ظ��ı����ɲ鿴.����㲻��̸��Щ��������ĵ�ʲô��.");
		replay.setContent(context.toString());
		writer.print(replay.toXML());
	}

}
