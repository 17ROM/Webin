package com.webin.core.script;

import java.io.PrintWriter;

import com.webin.core.db.DbOperate;
import com.webin.core.robot.HexString;
import com.webin.core.wechat.MsgTag;
import com.webin.core.wechat.TextMsg;

public class ChatMenu implements IHandle {
	private DbOperate dbop = new DbOperate();

	private void menuNormal(MsgTag tag, PrintWriter writer, String msg) {
		TextMsg replay = new TextMsg(tag.getMsg());
		replay.setContent(msg);
		writer.print(replay.toXML());
	}
	
	private void menuUpdate(MsgTag tag, PrintWriter writer){
		String content = "谢谢提交已收录";
		menuNormal(tag, writer, content);
	}
	
	private void handleOtherWord(MsgTag tag, PrintWriter writer) {
		String content = "然后呢";
		menuNormal(tag, writer, content);
	}
	
	private boolean isHandleRecorder(MsgTag tag, PrintWriter writer){
		String[] words = tag.Content.split(",");
		if (words.length == 1) {
			words = tag.Content.split("，");
		}
		if (words.length == 3 && words[0].equals("收录")){
			String code = HexString.StringtoHex(words[1]);
			boolean result = dbop.executeQuery(code);
			if (result) {
				dbop.executeUpdate(code, words[2]);
			} else {
				dbop.executeInsert(code, words[2]);
			}
			menuUpdate(tag, writer);
			return true;
		}
		return false;
	}

	/*
	 * WebinRobot _id _code _weight _reply0
	 */
	private boolean isHandleByRobot(MsgTag tag, PrintWriter writer) {
		String code = HexString.StringtoHex(tag.Content);
		String result = dbop.executeQueryResult(code);
		if (result != null) {
			menuNormal(tag, writer, result);
			return true;
		}
		return false;
	}

	@Override
	public void handleMsg(MsgTag tag, PrintWriter writer) {
		if (isHandleRecorder(tag, writer)){
			return;
		}
		if (!isHandleByRobot(tag, writer)){
			handleOtherWord(tag, writer);
		}
	}

}
