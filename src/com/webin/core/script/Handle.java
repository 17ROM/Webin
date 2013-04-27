package com.webin.core.script;

import java.io.PrintWriter;

import com.webin.core.wechat.Msg;
import com.webin.core.wechat.MsgTag;
import com.webin.core.wechat.TextMsg;

public class Handle {
	
	private static MenuManager mMenuManager = new MenuManager();
	
	private static void handleSubscribe(MsgTag tag, PrintWriter writer){
		TextMsg replay = new TextMsg(tag.getMsg());
		StringBuilder context = new StringBuilder();
		context.append("���,��ӭ��עĲ���ƽ̨!");
		context.append("�ظ�[����]���Բ鿴����!");
		replay.setContent(context.toString());
		writer.print(replay.toXML());
	}
	
	private static void handleTextMsg(MsgTag tag, PrintWriter writer){
		TextMsg replay = new TextMsg(tag.getMsg());
		StringBuilder context = new StringBuilder();
		context.append("�ظ�[����]���Բ鿴����!");
		replay.setContent(context.toString());
		writer.print(replay.toXML());
	}
	
	public static void HandleWeChat(MsgTag tag, PrintWriter writer) {
		//
		if (tag == null){
			return;
		}
		// ��ע��Ϣ
		if (tag.isMsgType(Msg.MSG_GET_EVENT)) {
			handleSubscribe(tag, writer);
		} else if (tag.isMsgType(Msg.MSG_GET_TEXT)){
			// �������ģʽ
			if (mMenuManager.isMenuMode(tag.Content)){
				mMenuManager.HandleMenuMsg(tag, writer);
			}else {
				handleTextMsg(tag, writer);
			}
		}
	}

	
}
