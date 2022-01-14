package com.loctek.intelligent_bed_monitoring_platform.realTime_dataCollection.das.socket.request;


public class UpdateDataInfoAckRequest  extends BaseRequest{
	int dataLen;

	public int getDataLen() {
		return dataLen;
	}

	public void setDataLen(int dataLen) {
		this.dataLen = dataLen;
	}
	
	
}
