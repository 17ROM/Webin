package com.webin.core.wechat;

import java.io.InputStream;

import com.thoughtworks.xstream.annotations.XStreamAlias;

public class LinkMsg extends Msg {
	@XStreamAlias("Title")
	private String Title;
	@XStreamAlias("Description")
	private String Description;
	@XStreamAlias("Url")
	private String Url;

	public String getTitle() {
		return Title;
	}

	public String getDescription() {
		return Description;
	}

	public String getUrl() {
		return Url;
	}
	
	public static LinkMsg toBean(InputStream xml) {
		return MsgXml.toBean(xml, LinkMsg.class);
	}
}
