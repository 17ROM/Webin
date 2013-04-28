package com.webin.core.script;

import java.io.PrintWriter;

import com.webin.core.wechat.MsgTag;
import com.webin.core.wechat.TextMsg;

public class InfoMenu implements IHandle {
	private String mWord = "��Ϣ|Ů����|���|����|��|��|��|ܳ|��|��|��ʦ|��ʪ|����|���|˯��|��Ƭ|����|��������";

	@Override
	public boolean isMsg(String msg) {
		/*boolean bismsg = false;
		String[] words = mWord.split("|");
		for (String word : words) {
			if (msg.contains(word)) {
				bismsg = true;
				break;
			}
		}*/
		return mWord.contains(msg);
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
		}else if (tag.Content.equals("��")||tag.Content.equals("ܳ")){
			context.append("�ݶ���ţ�Թ��ˣ��������");
		}else if (tag.Content.equals("��ʦ")||tag.Content.equals("��ʪ")){
			context.append("�����ӷ��ҷ�ȱ���");
		}else if (tag.Content.equals("����")){
			context.append("�����ö����");
		}else if (tag.Content.equals("����")){
			context.append("�����ö����");
		}else if (tag.Content.equals("˯��")){
			context.append("�Ա�û��˯��");
		}else if (tag.Content.equals("��Ƭ")){
			context.append("���Ĳ�˧,û����Ƭ");
		}else if (tag.Content.equals("����")){
			context.append("���˵��ʲô��");
		}else if (tag.Content.equals("��������")||tag.Content.equals("������")){
			context.append("˵�������İ�");
		}else if (tag.Content.equals("���")){
			context.append("����");
		}else {
			context.append("Ȼ����");
		}
		menuNormal(tag, writer, context.toString());
	}

	@Override
	public void handleMsg(MsgTag tag, PrintWriter writer) {
		if (tag.Content.equals("��Ϣ")){
			menuInfo(tag, writer);
		}else if (tag.Content.equals("Ů����")){
			menuGirl(tag, writer);
		}else if (tag.Content.equals("���")){
			menuMarry(tag, writer);
		}else if(tag.Content.equals("����")){
			menuPhone(tag, writer);
		}else {
			menuShare(tag, writer);
		}
	}

}
