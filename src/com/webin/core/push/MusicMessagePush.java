package com.webin.core.push;

import com.webin.core.ErrorException;
import com.webin.core.pull.MessagePull;

public class MusicMessagePush extends MessagePush {
	private String mFuncFlag;
	private MusicInfo mMusicInfo;
	
	public MusicMessagePush(MessagePull vMsgPull) {
		super();
		setToUserName(vMsgPull.getToUserName());
		setFromUserName(vMsgPull.getFromUserName());
		setCreateTime();
		setMsgType(MSG_MUSIC);
	}

	public void setMusicInfo(MusicInfo musicinfo) {
		mMusicInfo = musicinfo;
		set(false, "Music", mMusicInfo.toString());
	}
	
	public void setFuncFlag(String funcFlag) {
		this.mFuncFlag = funcFlag;
		set(false, "FuncFlag", mFuncFlag);
	}
	
	@Override
	public String toString() {
		if (mMusicInfo == null || mFuncFlag == null) {
			ErrorException.NullPointer(mMusicInfo, mFuncFlag);
		}
		return super.toString();
	}
	
	public static class MusicInfo {
		private String mTitle;
		private String mDescription;
		private String mMusicUrl;
		private String mHQMusicUrl;
		
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
		public String getMusicUrl() {
			return mMusicUrl;
		}
		public void setMusicUrl(String musicUrl) {
			mMusicUrl = musicUrl;
		}
		public String getHQMusicUrl() {
			return mHQMusicUrl;
		}
		public void setHQMusicUrl(String hQMusicUrl) {
			mHQMusicUrl = hQMusicUrl;
		}

		private String formatMusicInfo() {
			StringBuilder result = new StringBuilder();
			result.append("<Title><![CDATA[" + mTitle + "]]></Title>\n");
			result.append("<Description><![CDATA[" + mDescription + "]]></Description>\n");
			result.append("<MusicUrl><![CDATA[" + mMusicUrl + "]]></MusicUrl>\n");
			result.append("<HQMusicUrl><![CDATA[" + mHQMusicUrl + "]]></HQMusicUrl>\n");
			return result.toString();
		}

		@Override
		public String toString() {
			return formatMusicInfo();
		}
	}
}
