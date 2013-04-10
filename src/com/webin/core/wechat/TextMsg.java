package com.webin.core.wechat;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("xml")
public class TextMsg extends Msg {
	@XStreamAlias("Content")
	public String Content;

	/**
	 * @see POST
	 */
	public TextMsg(Msg msg) {
		setToUserName(msg.FromUserName);
		setFromUserName(msg.ToUserName);
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
	/**
	 * @see GET
	 */
	public static TextMsg toBean(String xml) {
		return MsgXml.toBean(xml, TextMsg.class);
	}
}
