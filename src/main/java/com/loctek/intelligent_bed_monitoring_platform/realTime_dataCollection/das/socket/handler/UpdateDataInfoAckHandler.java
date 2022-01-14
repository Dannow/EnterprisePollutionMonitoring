package com.loctek.intelligent_bed_monitoring_platform.realTime_dataCollection.das.socket.handler;


import com.loctek.intelligent_bed_monitoring_platform.realTime_dataCollection.das.socket.parser.UpdateDataInfoAckParser;
import com.loctek.intelligent_bed_monitoring_platform.realTime_dataCollection.das.socket.request.UpdateDataInfoAckRequest;
import com.loctek.intelligent_bed_monitoring_platform.realTime_dataCollection.das.socket.server.IntelligentMattressProtocol;

public class UpdateDataInfoAckHandler extends  BaseRequestHandler
{

    public void handle(IntelligentMattressProtocol protocol)
    {
    	

    	UpdateDataInfoAckParser parser = new UpdateDataInfoAckParser();
        UpdateDataInfoAckRequest parse = parser.parse(protocol);

    }

}