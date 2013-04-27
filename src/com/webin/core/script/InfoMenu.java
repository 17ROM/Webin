package com.webin.core.script;

import java.io.PrintWriter;

import com.webin.core.wechat.MsgTag;
import com.webin.core.wechat.TextMsg;

public class InfoMenu implements IHandle {
	private int mItemWord = -1;
	private String[] mWord = new String[] { "��Ϣ", "Ů����", "���", "����", "��|��|��|ܳ|��|��ʦ"};

	@Override
	public boolean isMsg(String msg) {
		mItemWord = -1;
		boolean bismsg = false;
		for (String word : mWord) {
			mItemWord++;
			if (word.contains("|")){
				if (word.contains(msg)){
					bismsg = true;
					break;
				}
			}else {
				if (msg.contains(word)) {
					bismsg = true;
					break;
				}
			}
		}
		return bismsg;
	}

	private void menuInfo(MsgTag tag, PrintWriter writer) {
		StringBuilder context = new StringBuilder();
		context.append("Hi~\n");
		context.append("~/��Ϣ\n");
		context.append("�ǳ�: ����\n");
		context.append("����: �Ĵ� �ɶ�\n");
		context.append("����ǩ��: All shall be well, and I shall have you.");
		menuNormal(tag, writer, context.toString());
	}

	private void menuGirl(MsgTag tag, PrintWriter writer) {
		StringBuilder context = new StringBuilder();
		context.append("Ů���ѻ�������ĸ�����.");
		menuNormal(tag, writer, context.toString());
	}

	private void menuMarry(MsgTag tag, PrintWriter writer) {
		StringBuilder context = new StringBuilder();
		context.append("��黹�簡����ɶ��.");
		menuNormal(tag, writer, context.toString());
	}

	private void menuNormal(MsgTag tag, PrintWriter writer, String msg) {
		TextMsg replay = new TextMsg(tag.getMsg());
		StringBuilder context = new StringBuilder();
		replay.setContent(msg);
		writer.print(replay.toXML());
	}

	private void menuPhone(MsgTag tag, PrintWriter writer) {
		StringBuilder context = new StringBuilder();
		context.append("110.");
		menuNormal(tag, writer, context.toString());
	}
	
	private void menuShare(MsgTag tag, PrintWriter writer){
		StringBuilder context = new StringBuilder();
		if (tag.Content.equals("��")){
			context.append("��");
		}else if (tag.Content.equals("����")){
			context.append("����");
		}else if (tag.Content.equals("�ڸ�ɶ")){
			context.append("��ˣ");
		}else if (tag.Content.equals("��")){
			context.append("����̫����ɹ��");
		}else if (tag.Content.equals("��")){
			context.append("�����ܸɾ��ˣ��㻹Ҫ����");
		}else if (tag.Content.equals("��")|tag.Content.equals("ܳ")){
			context.append("�ݶ���ţ�Թ��ˣ��������");
		}else if (tag.Content.equals("��ʦ")){
			context.append("�����ӷ��ҷ�ȱ���");
		}
		menuNormal(tag, writer, context.toString());
	}

	@Override
	public void handleMsg(MsgTag tag, PrintWriter writer) {
		switch (mItemWord++){
		case 0:
			menuInfo(tag, writer);
			break;
		case 1:
			menuGirl(tag, writer);
			break;
		case 2:
			menuMarry(tag, writer);
			break;
		case 3:
			menuPhone(tag, writer);
		case 4:
			menuShare(tag, writer);
			break;
		}
	}

}
