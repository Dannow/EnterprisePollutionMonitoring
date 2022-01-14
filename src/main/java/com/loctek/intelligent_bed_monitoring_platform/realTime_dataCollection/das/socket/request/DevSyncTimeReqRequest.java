package com.loctek.intelligent_bed_monitoring_platform.realTime_dataCollection.das.socket.request;

import lombok.Getter;
import lombok.Setter;

/**
 * 体征信息 BedStatusRequest xieyonggao xieyonggao 2018年5月29日 下午3:58:02
 * 
 * @version 1.0.0
 */
@Getter
@Setter
public class DevSyncTimeReqRequest extends BaseRequest
{
    //设备当前时间,秒
    private Long time;

	/**
	 * @return the time
	 */
	public Long getTime() {
		return time;
	}

	/**
	 * @param time the time to set
	 */
	public void setTime(Long time) {
		this.time = time;
	}
    
}
