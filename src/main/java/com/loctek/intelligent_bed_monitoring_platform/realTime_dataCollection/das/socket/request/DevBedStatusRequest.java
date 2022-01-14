package com.loctek.intelligent_bed_monitoring_platform.realTime_dataCollection.das.socket.request;


/**
 * 体征信息 BedStatusRequest xieyonggao xieyonggao 2018年5月29日 下午3:58:02
 * 
 * @version 1.0.0
 */
public class DevBedStatusRequest extends BaseRequest
{
    public Long getTime() {
		return time;
	}
	public void setTime(Long time) {
		this.time = time;
	}
	public Byte getStatus() {
		return status;
	}
	public void setStatus(Byte status) {
		this.status = status;
	}
	//当前体征信息的时间，秒
    private Long time;
    //0：Unknow，1：在床，2：离床
    private Byte status;
    
}
