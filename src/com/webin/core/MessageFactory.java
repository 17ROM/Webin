package com.webin.core;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

import com.webin.core.wechat.ImgMsg;
import com.webin.core.wechat.Msg;
import com.webin.core.wechat.MsgTag;
import com.webin.core.wechat.MusicMsg;
import com.webin.core.wechat.TextMsg;

public class MessageFactory {
	private static MessageFactory _inst = null;

	private MessageFactory() {
	}

	public static MessageFactory getInstance() {
		if (_inst == null) {
			_inst = new MessageFactory();
		}
		return _inst;
	}

	public Msg GetMsg(InputStream xml) {
		MsgTag tag = MsgTag.toBean(xml);
		return tag.getMsg();
	}

	public Msg PostMsg(Msg msg, String type) {
		Msg vmsg = null;
		if (Msg.MSG_POST_TEXT.equals(type)) {
			vmsg = new TextMsg(msg);
		} else if (Msg.MSG_POST_NEWS.equals(type)) {
			vmsg = new ImgMsg(msg);
		} else if (Msg.MSG_POST_MUSIC.equals(type)) {
			vmsg = new MusicMsg(msg);
		}
		return vmsg;
	}

	public void HandleGetMsg(InputStream inputStream, PrintWriter writer) throws IOException {
		MsgTag tag = MsgTag.toBean(inputStream);
		if (tag != null) {
		}
	}
}
