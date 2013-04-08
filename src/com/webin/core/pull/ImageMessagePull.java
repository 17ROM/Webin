package com.webin.core.pull;

public class ImageMessagePull extends MessagePull {
	private String mPicUrl;

	public ImageMessagePull(MessagePullObj msg) {
		super(msg);
		formatMessageWeb(msg);
	}
	
	private void formatMessageWeb(MessagePullObj msg){
		setPicUrl(msg.get("PicUrl"));
	}
	
	private void setPicUrl(String picUrl){
		mPicUrl = picUrl;
	}
	
	public String getPicUrl(){
		return mPicUrl;
	}
	
	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();
		result.append(super.toString());
		result.append("[PicUrl=" + mPicUrl + "]\n");
		return result.toString();
	}

}
