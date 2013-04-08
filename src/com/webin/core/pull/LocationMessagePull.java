package com.webin.core.pull;

public class LocationMessagePull extends MessagePull {
	private String mLocation_X;
	private String mLocation_Y;
	private String mScale;
	private String mLabel;

	public String getLocation_X() {
		return mLocation_X;
	}

	public void setLocation_X(String location_X) {
		this.mLocation_X = location_X;
	}

	public String getLocation_Y() {
		return mLocation_Y;
	}

	public void setLocation_Y(String location_Y) {
		this.mLocation_Y = location_Y;
	}

	public String getScale() {
		return mScale;
	}

	public void setScale(String scale) {
		this.mScale = scale;
	}

	public String getLabel() {
		return mLabel;
	}

	public void setLabel(String label) {
		this.mLabel = label;
	}

	public LocationMessagePull(MessagePullObj msg) {
		super(msg);
		formatMessageWeb(msg);
	}

	private void formatMessageWeb(MessagePullObj msg) {
		setLocation_X(msg.get("Location_X"));
		setLocation_Y(msg.get("Location_Y"));
		setScale(msg.get("Scale"));
		setLabel(msg.get("Label"));
	}
	
	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();
		result.append(super.toString());
		result.append("[Location_X=" + mLocation_X + "]\n");
		result.append("[Location_Y=" + mLocation_Y + "]\n");
		result.append("[Scale=" + mScale + "]\n");
		result.append("[Label=" + mLabel + "]\n");
		return result.toString();
	}

}
