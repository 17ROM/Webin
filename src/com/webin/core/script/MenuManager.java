package com.webin.core.script;

import java.io.PrintWriter;

import com.webin.core.wechat.MsgTag;


public class MenuManager {
	private ChatMenu mChatMenu = new ChatMenu();
	private HelpMenu mHelpMenu = new HelpMenu();

	public boolean isHelpMode(String content) {
		return HelpMenu.isMsg(content);
	}

	public void handleHelpMsg(MsgTag tag, PrintWriter writer) {
		mHelpMenu.handleMsg(tag, writer);
	}

	public void handleChatMsg(MsgTag tag, PrintWriter writer) {
		mChatMenu.handleMsg(tag, writer);
	}

}
