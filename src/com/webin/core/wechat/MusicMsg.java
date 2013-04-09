package com.webin.core.wechat;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("xml")
public class MusicMsg extends Msg {
	@XStreamAlias("Music")
	private MusicInfo Music;

	public MusicMsg(Msg vMsgPull) {
		setToUserName(vMsgPull.getToUserName());
		setFromUserName(vMsgPull.getFromUserName());
		setCreateTime();
		setMsgType(MSG_POST_MUSIC);
	}

	public void setMusicInfo(MusicInfo musicinfo) {
		this.Music = musicinfo;
	}

	public static class MusicInfo {
		@XStreamAlias("Title")
		private String Title;
		@XStreamAlias("Description")
		private String Description;
		@XStreamAlias("MusicUrl")
		private String MusicUrl;
		@XStreamAlias("HQMusicUrl")
		private String HQMusicUrl;

		public void setTitle(String title) {
			this.Title = title;
		}

		public void setDescription(String description) {
			this.Description = description;
		}

		public void setMusicUrl(String musicUrl) {
			this.MusicUrl = musicUrl;
		}

		public void setHQMusicUrl(String hQMusicUrl) {
			this.HQMusicUrl = hQMusicUrl;
		}
	}
	
	public String toXML() {
		return MsgXml.toXML(this);
	}
}
