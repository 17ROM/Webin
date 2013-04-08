package com.webin.core.pull;

public class LinkMessagePull extends MessagePull {
	private String mTitle;
	private String mDescription;
	private String mUrl;

	public LinkMessagePull(MessagePullObj msg) {
		super(msg);
		formatMessageWeb(msg);
	}
	
	private void formatMessageWeb(MessagePullObj msg) {
		setTitle(msg.get("Title"));
		setDescription(msg.get("Description"));
		setUrl(msg.get("Url"));
	}
	
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

	public String getUrl() {
		return mUrl;
	}

	public void setUrl(String url) {
		mUrl = url;
	}
	
	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();
		result.append(super.toString());
		result.append("[Title=" + mTitle + "]\n");
		result.append("[Description=" + mDescription + "]\n");
		result.append("[Url=" + mUrl + "]\n");
		return result.toString();
	}

}
