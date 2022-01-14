package com.loctek.intelligent_bed_monitoring_platform.realTime_dataCollection.das.socket.request;


public class ServerRequest extends BaseRequest{
	private byte[] content;

	/**
	 * @return the content
	 */
	public byte[] getContent() {
		return content;
	}

	/**
	 * @param content the content to set
	 */
	public void setContent(byte[] content) {
		this.content = content;
	}
}
