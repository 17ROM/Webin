package com.webin.core.script;

import java.io.PrintWriter;

import com.webin.core.wechat.MsgTag;

public class MenuManager {
	
	private IHandle help = new HelpMenu();
	private IHandle info = new InfoMenu();
	
	private int mode = -1;

	public boolean isMenuMode(String content) {
		if (help.isMsg(content)){
			mode = 0;
			return true;
		}
		if (info.isMsg(content)){
			mode = 1;
			return true;
		}
		return false;
	}

	public void HandleMenuMsg(MsgTag tag, PrintWriter writer) {
		switch (mode){
		case 0:
			help.handleMsg(tag, writer);
			break;
		case 1:
			info.handleMsg(tag, writer);
			break;
		}
	}

}
