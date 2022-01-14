package com.loctek.intelligent_bed_monitoring_platform.realTime_dataCollection.das.socket.handler;

public interface DataPackageConstants
{
    static final int COMMAND_CODE_INDEX = -1;
    
    static final int UPDATE_PACKET_SIZE = 2048;
    
    static final int SEC = 1000;
    static final int UPDATE_TIME_OUT_SEC = SEC*5;
    
    static final int DEV_DEVICE_INFO = 0x01;
    static final int DEV_DEVICE_STATUS = 0x02;
    static final int DEV_SYNC_TIME_REQ = 0x03;
    static final int DEV_BODY_SIG = 0x04;
    static final int DEV_RAW_DATA = 0x05;
    static final int DEV_BED_STATUS = 0x06;
    static final int DEV_CALIBRATION_ACK = 0x07;
    static final int DEV_UPGRADE_REQ = 0x08;
    static final int DEV_UPGRADE_ACK = 0x09;
    static final int DEV_MODE_ACK = 0x0A;
    static final int DEV_OFFLINEDATA_UPLOAD_ACK = 0x0B;
    static final int DEV_OFFLINE_DATA = 0x0C;
    static final int DEV_SOCKETIP_ACK = 0x0D;

    static final int DEV_CMD_RECEIVE_STA = 0x10;
    static final int DEV_UPGRADE_DATAINFO_ACK = 0x11;
    
    
    
    static final int DEV_2G_APGS_INFO = 0x12;
    static final int DEV_SURRONGDINGS_INFO = 0x13;
    
    /**
     * 设备基本信息
     */
    static final int SER_DEVICE_INFO = 0x81;
    
    /**
     * 设备状态
     */
    static final int SER_DEVICE_STATUS = 0x82;
    static final int SER_SYNC_TIME_REQ = 0x83;
    static final int SER_SYNC_TIME_ACK = 0x84;
    static final int SER_CALIBRATION_REQ = 0x85;
    static final int SER_UPGRADE_REQ = 0x86;
    static final int SER_UPGRADE_ACK = 0x87;
    static final int SER_UPGRADE_DATA = 0x88;
    static final int SER_SET_MODE  = 0x89;
    static final int SER_OFFLINEDATA_UPLOAD_REQ = 0x8A;
    static final int SER_OFFLINEDATA_ACK = 0x8B;
    static final int SER_SET_SOCKETIP_REQ = 0x8C;
    static final int SER_SET_AUTODEVICESTATUS = 0x91;
    static final int SER_GET_2G_AGPS = 0x92;
    static final int SER_GET_SURROUNDINGS = 0x93;
    
    
   
    
    
}
