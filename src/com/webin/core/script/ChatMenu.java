package com.webin.core.script;

import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.webin.core.robot.RobotDatabase;
import com.webin.core.wechat.MsgTag;
import com.webin.core.wechat.TextMsg;

public class ChatMenu implements IHandle {
	private RobotDatabase mRobotDatabase = RobotDatabase.getDefault();

	private void menuNormal(MsgTag tag, PrintWriter writer, String msg) {
		TextMsg replay = new TextMsg(tag.getMsg());
		replay.setContent(msg);
		writer.print(replay.toXML());
	}

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

	private void handleOtherWord(MsgTag tag, PrintWriter writer) {
		String content = "È»ºóÄØ";
		menuNormal(tag, writer, content);
	}

	@Override
	public void handleMsg(MsgTag tag, PrintWriter writer) {
		if (!isHandleByRobot(tag, writer)){
			handleOtherWord(tag, writer);
		}
	}

}
