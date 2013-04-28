package com.webin.core.script;

import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.webin.core.db.RobotDatabase;
import com.webin.core.wechat.MsgTag;
import com.webin.core.wechat.TextMsg;

public class ChatMenu implements IHandle {
	private RobotDatabase mRobotDatabase = RobotDatabase.getDefault();

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
			ResultSet result = mRobotDatabase.executeQuery(words[1]);
			try {
				if (result.first()){
					mRobotDatabase.executeUpdate(words[1], words[2]);
				}else{
					mRobotDatabase.executeInsert(words[1], words[2]);
				}
			} catch (SQLException e) {
				e.printStackTrace();
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
		ResultSet result = mRobotDatabase.executeQuery(tag.Content);
		try {
			if (result.first()){
				int weight = result.getInt(3);
				String content = result.getString(3 + weight);
				menuNormal(tag, writer, content);
				return true;
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
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
