package com.loctek.intelligent_bed_monitoring_platform.realTime_dataCollection.das.socket.handler;


import com.loctek.intelligent_bed_monitoring_platform.realTime_dataCollection.das.socket.parser.DevBedStatusParser;
import com.loctek.intelligent_bed_monitoring_platform.realTime_dataCollection.das.socket.request.DevBedStatusRequest;
import com.loctek.intelligent_bed_monitoring_platform.realTime_dataCollection.das.socket.server.IntelligentMattressProtocol;

/**
 * DevBedStatusHandler xieyonggao xieyonggao 2018年5月30日 下午2:36:08
 * 
 * @version 1.0.0
 */
public class DevBedStatusHandler implements RequestHandler
{
    public void handle(IntelligentMattressProtocol protocol)
    {

        DevBedStatusParser parser = new DevBedStatusParser();
        DevBedStatusRequest request = parser.parse(protocol);

    }
}
