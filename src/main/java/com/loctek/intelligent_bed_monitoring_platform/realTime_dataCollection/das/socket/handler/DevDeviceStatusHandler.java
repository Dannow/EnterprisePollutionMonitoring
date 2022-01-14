package com.loctek.intelligent_bed_monitoring_platform.realTime_dataCollection.das.socket.handler;


import com.loctek.intelligent_bed_monitoring_platform.realTime_dataCollection.das.socket.parser.DevDeviceStatusParser;
import com.loctek.intelligent_bed_monitoring_platform.realTime_dataCollection.das.socket.request.DevDeviceStatusRequest;
import com.loctek.intelligent_bed_monitoring_platform.realTime_dataCollection.das.socket.server.IntelligentMattressProtocol;

public class DevDeviceStatusHandler implements RequestHandler
{
    public void handle(IntelligentMattressProtocol protocol)
    {
        DevDeviceStatusParser parser = new DevDeviceStatusParser();
        DevDeviceStatusRequest devDeviceStatusRequest = parser.parse(protocol);
        
    }
    
}
