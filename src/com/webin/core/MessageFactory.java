package com.webin.core;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

import com.webin.core.wechat.EventMsg;
import com.webin.core.wechat.ImgMsg;
import com.webin.core.wechat.LinkMsg;
import com.webin.core.wechat.LocationMsg;
import com.webin.core.wechat.Msg;
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
		Msg msg = Msg.toBean(xml);
		String type = msg.getMsgType();
		msg = null;
		if (Msg.MSG_GET_TEXT.equals(type)) {
			msg = TextMsg.toBean(xml);
		} else if (Msg.MSG_GET_LOCATION.equals(type)) {
			msg = LocationMsg.toBean(xml);
		} else if (Msg.MSG_GET_LINK.equals(type)) {
			msg = LinkMsg.toBean(xml);
		} else if (Msg.MSG_GET_IMAGE.equals(type)) {
			msg = ImgMsg.toBean(xml);
		} else if (Msg.MSG_GET_EVENT.equals(type)) {
			msg = EventMsg.toBean(xml);
		}
		return msg;
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
		Msg msg = GetMsg(inputStream);
		if (msg != null) {
			HandlePostMsg(msg, writer);
		}
	}

	public void HandlePostMsg(Msg msg, PrintWriter writer) throws IOException {
		TextMsg txt = new TextMsg(msg);
		txt.setContent("ÄãºÃ°¡");
		txt.setFuncFlag("0");
		writer.print(txt.toXML());
	}
}
