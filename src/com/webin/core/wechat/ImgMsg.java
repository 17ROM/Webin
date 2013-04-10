package com.webin.core.wechat;

import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

@XStreamAlias("xml")
public class ImgMsg extends Msg {
	@XStreamAlias("Articles")
	private ImgMsgList Articles;
	@XStreamAlias("ArticleCount")
	private String ArticleCount;
	@XStreamAlias("PicUrl")
	private String PicUrl;

	/**
	 * @see POST
	 */
	public ImgMsg(Msg msg) {
		setToUserName(msg.FromUserName);
		setFromUserName(msg.ToUserName);
		setCreateTime();
		setMsgType(MSG_POST_NEWS);
	}
	
	public String getPicUrl() {
		return PicUrl;
	}

	public void setImgMsgList(ImgMsgList msg) {
		this.Articles = msg;
		setArticleCount();
	}

	private void setArticleCount() {
		this.ArticleCount = String.valueOf(Articles.getArticleCount());
	}

	public static class ImgMsgList {
		@XStreamImplicit(itemFieldName = "item")
		private List<ImageText> item;

		public int getArticleCount() {
			return item.size();
		}

		public void setItemList(List<ImageText> items) {
			this.item = items;
		}
	}

	public static class ImageText {
		@XStreamAlias("Title")
		private String Title;
		@XStreamAlias("Description")
		private String Description;
		@XStreamAlias("PicUrl")
		private String PicUrl;
		@XStreamAlias("Url")
		private String Url;

		public void setTitle(String title) {
			this.Title = title;
		}

		public void setDescription(String description) {
			this.Description = description;
		}

		public void setPicUrl(String picUrl) {
			this.PicUrl = picUrl;
		}

		public void setUrl(String url) {
			this.Url = url;
		}
	}

	public String toXML() {
		return MsgXml.toXML(this);
	}

	/**
	 * @see GET
	 */
	public static ImgMsg toBean(String xml) {
		return MsgXml.toBean(xml, ImgMsg.class);
	}
}
