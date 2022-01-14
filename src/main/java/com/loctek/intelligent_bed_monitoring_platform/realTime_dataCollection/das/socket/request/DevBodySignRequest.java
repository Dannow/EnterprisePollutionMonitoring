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
public class DevBodySignRequest extends BaseRequest
{
    //循环计数
    private Integer number;
    
    //当前体征信息的时间
    private Long time;

    public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public Long getTime() {
		return time;
	}

	public void setTime(Long time) {
		this.time = time;
	}

	public Byte getBed() {
		return bed;
	}

	public void setBed(Byte bed) {
		this.bed = bed;
	}

	public short getHeartRate() {
		return (short) (0xff&heartRate);
	}

	public void setHeartRate(Byte heartRate) {
		this.heartRate = heartRate;
	}

	public short getBreathRate() {
		return (short) (0xff&BreathRate);
	}

	public void setBreathRate(Byte breathRate) {
		BreathRate = breathRate;
	}

	public Short getHeartRateCal() {
		return heartRateCal;
	}

	public void setHeartRateCal(Short heartRateCal) {
		this.heartRateCal = heartRateCal;
	}

	public Byte getMotion() {
		return motion;
	}

	public void setMotion(Byte motion) {
		this.motion = motion;
	}

	private Byte bed;
    


    private Byte heartRate;

    private Byte BreathRate;
    
    
    private Short heartRateCal;
    
    //体动情况 0：正常1：信号学习中2：信号过弱3：有微弱运动4：有普通运动5：有大幅度运动
    private Byte motion;
    
}
