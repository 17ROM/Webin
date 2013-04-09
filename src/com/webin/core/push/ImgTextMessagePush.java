package com.webin.core.push;

import java.util.ArrayList;

import com.webin.core.ErrorException;
import com.webin.core.pull.MessagePull;

public class ImgTextMessagePush extends MessagePush {
	private ArrayList<ImageTextMessage> mMsgLists = new ArrayList<ImageTextMessage>();
	private String mFuncFlag;

	public ImgTextMessagePush(MessagePull vMsgPull) {
		super();
		setToUserName(vMsgPull.getToUserName());
		setFromUserName(vMsgPull.getFromUserName());
		setCreateTime();
		setMsgType(MSG_NEWS);
	}
	
	public void setImgTextMessage(ImageTextMessage msg){
		mMsgLists.add(msg);
	}
	
	public void setFuncFlag(String funcFlag) {
		this.mFuncFlag = funcFlag;
		set(false, "ArticleCount", String.valueOf(mMsgLists.size()));
		formatImgTextMessage();
		set(false, "FuncFlag", mFuncFlag);
	}
	
	private void formatImgTextMessage(){
		StringBuilder result = new StringBuilder();
		for (int i=0; i<mMsgLists.size(); i++){
			result.append(mMsgLists.get(i).toString());
		}
		set(false, "Articles", result.toString());
	}
	
	@Override
	public String toString() {
		if (mMsgLists.size() <= 0 || mFuncFlag == null) {
			ErrorException.NullPointer(mFuncFlag);
		}
		return super.toString();
	}

	public static class ImageTextMessage {
		private String mTitle;
		private String mDescription;
		private String mPicUrl;
		private String mUrl;

		public String getTitle() {
			return mTitle;
		}

		public void setTitle(String title) {
			mTitle = title;
		}

		public String getDescription() {
			return mDescription;
		}

		public void setDescription(String description) {
			mDescription = description;
		}

		public String getPicUrl() {
			return mPicUrl;
		}

		public void setPicUrl(String picUrl) {
			mPicUrl = picUrl;
		}

		public String getUrl() {
			return mUrl;
		}

		public void setUrl(String url) {
			mUrl = url;
		}

		private String formatImageTextMessage(){
			StringBuilder result = new StringBuilder();
			result.append("<item>\n");
			result.append("<Title><![CDATA[" + mTitle + "]]></Title>\n");
			result.append("<Description><![CDATA[" + mDescription + "]]></Description>\n");
			result.append("<PicUrl><![CDATA[" + mPicUrl + "]]></PicUrl>\n");
			result.append("<Url><![CDATA[" + mUrl + "]]></Url>\n");
			result.append("</item>\n");
			return result.toString();
		}
		
		@Override
		public String toString() {
			return formatImageTextMessage();
		}
	}
}
