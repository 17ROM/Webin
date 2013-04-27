package com.webin.core.script;

import java.io.PrintWriter;

import com.webin.core.wechat.MsgTag;
import com.webin.core.wechat.TextMsg;

public class InfoMenu implements IHandle {
	private int mItemWord = -1;
	private String[] mWord = new String[] { "信息", "女朋友", "结婚", "号码", "在|日|草|艹|擦|大师"};

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
		context.append("~/信息\n");
		context.append("昵称: 神算\n");
		context.append("地区: 四川 成都\n");
		context.append("个性签名: All shall be well, and I shall have you.");
		menuNormal(tag, writer, context.toString());
	}

	private void menuGirl(MsgTag tag, PrintWriter writer) {
		StringBuilder context = new StringBuilder();
		context.append("女朋友还养在丈母娘家里.");
		menuNormal(tag, writer, context.toString());
	}

	private void menuMarry(MsgTag tag, PrintWriter writer) {
		StringBuilder context = new StringBuilder();
		context.append("结婚还早啊，急啥嘛.");
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
		if (tag.Content.equals("在")){
			context.append("在");
		}else if (tag.Content.equals("在哪")){
			context.append("在这");
		}else if (tag.Content.equals("在干啥")){
			context.append("在耍");
		}else if (tag.Content.equals("日")){
			context.append("今天太阳不晒啊");
		}else if (tag.Content.equals("擦")){
			context.append("玻璃很干净了，你还要擦吗？");
		}else if (tag.Content.equals("草")|tag.Content.equals("艹")){
			context.append("草都被牛吃光了，你才来。");
		}else if (tag.Content.equals("大师")){
			context.append("阿弥陀佛，我佛慈悲。");
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
