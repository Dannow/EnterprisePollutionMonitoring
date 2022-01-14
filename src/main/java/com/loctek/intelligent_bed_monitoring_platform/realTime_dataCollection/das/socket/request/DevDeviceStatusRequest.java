package com.loctek.intelligent_bed_monitoring_platform.realTime_dataCollection.das.socket.request;

import lombok.Getter;
import lombok.Setter;

/**
 * 设备状态 DeviceStatusRequest xieyonggao xieyonggao 2018年5月29日 下午3:58:02
 * 
 * @version 1.0.0
 */
@Getter
@Setter
public class DevDeviceStatusRequest extends BaseRequest
{
    //设备状态：0：正常1：光纤故障2：采集信号异常3:监测环境不佳4:设备停用
    private byte deviceStatus;
    
    public byte getDeviceStatus() {
		return deviceStatus;
	}

	public void setDeviceStatus(byte deviceStatus) {
		this.deviceStatus = deviceStatus;
	}

	public byte getConnectStatus() {
		return connectStatus;
	}

	public void setConnectStatus(byte connectStatus) {
		this.connectStatus = connectStatus;
	}

	//网络状态,：0：无网络连接1：WIFI 连接正常2：移动通讯连接正常3：以太网连接正常
    private byte connectStatus;

}
