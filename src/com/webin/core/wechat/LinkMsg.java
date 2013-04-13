package com.webin.core.wechat;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("xml")
public class LinkMsg extends Msg {
	@XStreamAlias("Title")
	public String Title;
	@XStreamAlias("Description")
	public String Description;
	@XStreamAlias("Url")
	public String Url;
	
	public String getTitle() {
		return Title;
	}

	public String getDescription() {
		return Description;
	}

	public String getUrl() {
		return Url;
	}
	/**
	 * @see GET
	 */
	public static LinkMsg toBean(String xml) {
		return MsgXml.toBean(xml, LinkMsg.class);
	}
}
