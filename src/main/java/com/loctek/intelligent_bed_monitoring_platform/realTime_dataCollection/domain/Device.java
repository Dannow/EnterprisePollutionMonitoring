package com.loctek.intelligent_bed_monitoring_platform.realTime_dataCollection.domain;

public class Device {
	private String sn;
	private int online;
	
	public Device()//查询需要用到，没有就会查询失败
	{
		
	}
	public Device(String mSn, int online)
	{
		sn = mSn;
		this.online = online;
	}
	
	public String getSn() {
		return sn;
	}
	public void setSn(String sn) {
		this.sn = sn;
	}
	public int getOnline() {
		return online;
	}
	public void setOnline(int online) {
		this.online = online;
	}


	
}
