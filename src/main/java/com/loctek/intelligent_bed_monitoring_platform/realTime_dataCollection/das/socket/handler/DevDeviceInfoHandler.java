package com.loctek.intelligent_bed_monitoring_platform.realTime_dataCollection.das.socket.handler;


import com.loctek.intelligent_bed_monitoring_platform.realTime_dataCollection.das.socket.parser.DevDeviceInfoParser;
import com.loctek.intelligent_bed_monitoring_platform.realTime_dataCollection.das.socket.request.DevDeviceInfoRequest;
import com.loctek.intelligent_bed_monitoring_platform.realTime_dataCollection.das.socket.server.IntelligentMattressProtocol;

/**
 * 设备状态的处理类
 * DevDeviceInfoHandler
 * 
 * xieyonggao
 * xieyonggao
 * 2018年5月30日 下午2:36:33
 * 
 * @version 1.0.0
 *
 */
public class DevDeviceInfoHandler extends BaseRequestHandler
{
    public void handle(IntelligentMattressProtocol protocol)
    {
        DevDeviceInfoParser parser = new DevDeviceInfoParser();
        DevDeviceInfoRequest devDeviceInfoRequest = parser.parse(protocol);
    }
    
}
