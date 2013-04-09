package com.webin.core.push;

import java.util.ArrayList;

class MessagePushObj {
	private ArrayList<Message> mMsgs = new ArrayList<Message>();

	public MessagePushObj() {
		mMsgs.clear();
	}
	
	public void set(boolean format, String key, String value) {
		Message msg = new Message();
		msg.put(format, key, value);
		mMsgs.add(msg);
	}

	private String formatMessage() {
		StringBuilder result = new StringBuilder();
		result.append("<xml>\n");
		for (int i = 0; i < mMsgs.size(); i++) {
			Message msg = mMsgs.get(i);
			result.append(msg.toString());
		}
		result.append("</xml>\n");
		return result.toString();
	}

	@Override
	public String toString() {
		return formatMessage();
	}

	class Message {
		private String mKey;
		private String mValue;
		private boolean mFormat;

		public void put(boolean format, String key, String value) {
			mFormat = format;
			mKey = key;
			mValue = value;
		}

		public String getKey() {
			return mKey;
		}

		public String getValue() {
			return mValue;
		}

		private String formatMessage() {
			if (mFormat) {
				return "<" + mKey + "><![CDATA[" + mValue + "]]></" + mKey + ">\n";
			} else {
				return "<" + mKey + ">" + mValue + "</" + mKey + ">\n";
			}
		}

		@Override
		public String toString() {
			return formatMessage();
		}
	}
}
