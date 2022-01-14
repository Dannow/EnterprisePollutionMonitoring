package com.loctek.intelligent_bed_monitoring_platform.realTime_dataCollection.das.socket.request;


import com.loctek.intelligent_bed_monitoring_platform.realTime_dataCollection.domain.DevLocation;
import lombok.Data;

@Data
public class DevGsmGpsInfoRequest extends BaseRequest{
	 DevLocation mLocation;

	public DevLocation getmLocation() {
		return mLocation;
	}

	public void setmLocation(DevLocation mLocation) {
		this.mLocation = mLocation;
	}
	
}
