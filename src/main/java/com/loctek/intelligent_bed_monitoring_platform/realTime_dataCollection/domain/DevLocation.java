package com.loctek.intelligent_bed_monitoring_platform.realTime_dataCollection.domain;

import lombok.Data;

@Data
public class DevLocation {
	float latitude;
	float longitude;
	public DevLocation()
	{
		
	}
	public float getLatitude() {
		return latitude;
	}
	public void setLatitude(float latitude) {
		this.latitude = latitude;
	}
	public float getLongitude() {
		return longitude;
	}
	public void setLongitude(float longitude) {
		this.longitude = longitude;
	}
	
}
