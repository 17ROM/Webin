package com.webin.core.wechat;

import java.io.InputStream;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("xml")
public class TextMsg extends Msg {
	@XStreamAlias("Content")
	private String Content;

	public TextMsg(Msg vMsgPull) {
		setToUserName(vMsgPull.getToUserName());
		setFromUserName(vMsgPull.getFromUserName());
		setCreateTime();
		setMsgType(MSG_POST_TEXT);
	}

	public void setContent(String context) {
		this.Content = context;
	}

	public String getContent() {
		return Content;
	}

	public String toXML() {
		return MsgXml.toXML(this);
	}

	public static TextMsg toBean(InputStream xml) {
		return MsgXml.toBean(xml, TextMsg.class);
	}
}
