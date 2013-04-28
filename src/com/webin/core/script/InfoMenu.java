package com.webin.core.script;

import java.io.PrintWriter;

import com.webin.core.wechat.MsgTag;
import com.webin.core.wechat.TextMsg;

public class InfoMenu implements IHandle {
	private String mWord = "信息|女朋友|结婚|号码|在|日|草|艹|擦|晕|大师|大湿|你妹|你好|睡觉|照片|聊天|不给力啊";

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
		}else if (tag.Content.equals("草")||tag.Content.equals("艹")){
			context.append("草都被牛吃光了，你才来。");
		}else if (tag.Content.equals("大师")||tag.Content.equals("大湿")){
			context.append("阿弥陀佛，我佛慈悲。");
		}else if (tag.Content.equals("你妹")){
			context.append("你妹妹多大了");
		}else if (tag.Content.equals("你妹")){
			context.append("你妹妹多大了");
		}else if (tag.Content.equals("睡觉")){
			context.append("吃饱没就睡了");
		}else if (tag.Content.equals("照片")){
			context.append("长的不帅,没有照片");
		}else if (tag.Content.equals("聊天")){
			context.append("随便说点什么吧");
		}else if (tag.Content.equals("不给力啊")||tag.Content.equals("不给力")){
			context.append("说点其他的吧");
		}else if (tag.Content.equals("你好")){
			context.append("还好");
		}else {
			context.append("然后呢");
		}
		menuNormal(tag, writer, context.toString());
	}

	@Override
	public void handleMsg(MsgTag tag, PrintWriter writer) {
		if (tag.Content.equals("信息")){
			menuInfo(tag, writer);
		}else if (tag.Content.equals("女朋友")){
			menuGirl(tag, writer);
		}else if (tag.Content.equals("结婚")){
			menuMarry(tag, writer);
		}else if(tag.Content.equals("号码")){
			menuPhone(tag, writer);
		}else {
			menuShare(tag, writer);
		}
	}

}
