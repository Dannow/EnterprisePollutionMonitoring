package com.loctek.intelligent_bed_monitoring_platform.realTime_dataCollection.das.socket.request;


import lombok.Getter;
import lombok.Setter;


/**
 * 设备状态 DeviceStatusRequest xieyonggao 2018年5月29日 下午3:58:02
 * 
 * @version 1.0.0
 */
@Getter
@Setter
public class DevUpgradeAckRequest extends BaseRequest
{
    // 升级状态：0：开始，1：完成结束，2：出错重传，3：正常接收，4：异常终止，5：版本号相同终止
    private byte status;

    public byte getStatus() {
		return status;
	}

	public void setStatus(byte status) {
		this.status = status;
	}

	public byte getPacketIdH() {
		return packetIdH;
	}

	public void setPacketIdH(byte packetIdH) {
		this.packetIdH = packetIdH;
	}

	public byte getPacketIdL() {
		return packetIdL;
	}

	public void setPacketIdL(byte packetIdL) {
		this.packetIdL = packetIdL;
	}

	// 升级版本的序列号，即是第几包，避免发漏或是收发错乱。开始对应的是 0，出错重传，对应的是要重传的包；正常发送，对应下一个包
    /**
     * 升级版本的序列号，高位
     */
    private byte packetIdH;
    
    /**
     * 升级版本的序列号，低位
     */
    private byte packetIdL;

}
