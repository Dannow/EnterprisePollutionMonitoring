package com.loctek.intelligent_bed_monitoring_platform.realTime_dataCollection.das.socket.request;

import lombok.Getter;
import lombok.Setter;

/**
 * 设备基本信息 DeviceInfoRequest xieyonggao xieyonggao 2018年5月29日 下午3:58:02
 * 
 * @version 1.0.0
 */
@Getter
@Setter
public class DevDeviceInfoRequest extends BaseRequest
{

    private String fwVersion;

    private String algVersion;

    public String getFwVersion() {
		return fwVersion;
	}

	public void setFwVersion(String fwVersion) {
		this.fwVersion = fwVersion;
	}

	public String getAlgVersion() {
		return algVersion;
	}

	public void setAlgVersion(String algVersion) {
		this.algVersion = algVersion;
	}

	public String getBootVersion() {
		return bootVersion;
	}

	public void setBootVersion(String bootVersion) {
		this.bootVersion = bootVersion;
	}

	public Byte getUpgradeFlag() {
		return upgradeFlag;
	}

	public void setUpgradeFlag(Byte upgradeFlag) {
		this.upgradeFlag = upgradeFlag;
	}

	public String getSocketAUsingIp() {
		return socketAUsingIp;
	}

	public void setSocketAUsingIp(String socketAUsingIp) {
		this.socketAUsingIp = socketAUsingIp;
	}

	public Short getSocketAUsingPort() {
		return socketAUsingPort;
	}

	public void setSocketAUsingPort(Short socketAUsingPort) {
		this.socketAUsingPort = socketAUsingPort;
	}

	public Byte getSocketAUsingLen() {
		return socketAUsingLen;
	}

	public void setSocketAUsingLen(Byte socketAUsingLen) {
		this.socketAUsingLen = socketAUsingLen;
	}

	public String getSocketAIp() {
		return socketAIp;
	}

	public void setSocketAIp(String socketAIp) {
		this.socketAIp = socketAIp;
	}

	public Short getSocketAPort() {
		return socketAPort;
	}

	public void setSocketAPort(Short socketAPort) {
		this.socketAPort = socketAPort;
	}

	public Byte getSocketALen() {
		return socketALen;
	}

	public void setSocketALen(Byte socketALen) {
		this.socketALen = socketALen;
	}

	public String getMac() {
		return mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}

	public Byte getCalibration() {
		return calibration;
	}

	public void setCalibration(Byte calibration) {
		this.calibration = calibration;
	}

	public Byte getDeviceEnable() {
		return deviceEnable;
	}

	public void setDeviceEnable(Byte deviceEnable) {
		this.deviceEnable = deviceEnable;
	}

	public Byte getRawDataEnable() {
		return rawDataEnable;
	}

	public void setRawDataEnable(Byte rawDataEnable) {
		this.rawDataEnable = rawDataEnable;
	}

	public Byte getBodySignFreq() {
		return bodySignFreq;
	}

	public void setBodySignFreq(Byte bodySignFreq) {
		this.bodySignFreq = bodySignFreq;
	}

	public Byte getNetEableWifi() {
		return netEableWifi;
	}

	public void setNetEableWifi(Byte netEableWifi) {
		this.netEableWifi = netEableWifi;
	}

	public Byte getNetEableMobile() {
		return netEableMobile;
	}

	public void setNetEableMobile(Byte netEableMobile) {
		this.netEableMobile = netEableMobile;
	}

	public Byte getNetEableEthernet() {
		return netEableEthernet;
	}

	public void setNetEableEthernet(Byte netEableEthernet) {
		this.netEableEthernet = netEableEthernet;
	}

	public Byte getAutoCalEnable() {
		return AutoCalEnable;
	}

	public void setAutoCalEnable(Byte autoCalEnable) {
		AutoCalEnable = autoCalEnable;
	}

	public Byte getOfflineDataEnable() {
		return OfflineDataEnable;
	}

	public void setOfflineDataEnable(Byte offlineDataEnable) {
		OfflineDataEnable = offlineDataEnable;
	}

	public Byte getLogDataEnable() {
		return LogDataEnable;
	}

	public void setLogDataEnable(Byte logDataEnable) {
		LogDataEnable = logDataEnable;
	}

	private String bootVersion;

//    private String sn;

    private Byte upgradeFlag;

    private String socketAUsingIp;

    private Short socketAUsingPort;

    private Byte socketAUsingLen;

    private String socketAIp;

    private Short socketAPort;

    private Byte socketALen;

    private String mac;

    private Byte calibration;

    private Byte deviceEnable;

    private Byte rawDataEnable;

    private Byte bodySignFreq;

    private Byte netEableWifi;

    private Byte netEableMobile;

    private Byte netEableEthernet;

    private Byte AutoCalEnable;
    
    private Byte OfflineDataEnable;
    
    private Byte LogDataEnable;

    // 用户ID
    private int userID;
    
    
    

}
