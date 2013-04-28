package com.webin.core.script;

import java.io.PrintWriter;

import com.webin.core.wechat.MsgTag;
import com.webin.core.wechat.TextMsg;

public class HelpMenu implements IHandle{
	
	public static final String HELP_ZH = "����|����|��¼";

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
		String content = "����ĵ�ʲô��";
		menuNormal(tag, writer, content);
	}
	
	private void menuRecorder(MsgTag tag, PrintWriter writer){
		String content = "�ظ� ��¼,�ؼ���,�ظ�  ������¼����Ҫ�Ļظ�.\n ����: ��¼,��ϲ����,��Ҳϲ����";
		menuNormal(tag, writer, content);
	}
	
	private void handleHelp(MsgTag tag, PrintWriter writer){
		TextMsg replay = new TextMsg(tag.getMsg());
		StringBuilder context = new StringBuilder();
		context.append("Hi~\n");
		context.append("���ѿ�ͨ����:\n");
		context.append("1����¼\n");
		context.append("2������\n");
		context.append("�ظ��ı����ɲ鿴.����㲻��̸��Щ��������ĵ�ʲô��.");
		replay.setContent(context.toString());
		writer.print(replay.toXML());
	}

	@Override
	public void handleMsg(MsgTag tag, PrintWriter writer) {
		if (tag.Content.equals("����")){
			handleHelp(tag, writer);
		}else if (tag.Content.equals("����")){
			menuChat(tag, writer);
		}else if (tag.Content.equals("��¼")){
			menuRecorder(tag, writer);
		}
	}

}
