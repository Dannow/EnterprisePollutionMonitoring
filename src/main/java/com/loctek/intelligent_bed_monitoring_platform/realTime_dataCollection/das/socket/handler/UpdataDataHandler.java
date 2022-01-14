package com.loctek.intelligent_bed_monitoring_platform.realTime_dataCollection.das.socket.handler;


import com.loctek.intelligent_bed_monitoring_platform.realTime_dataCollection.das.socket.parser.DevUpdataReqParser;
import com.loctek.intelligent_bed_monitoring_platform.realTime_dataCollection.das.socket.server.IntelligentMattressProtocol;

public class UpdataDataHandler extends  BaseRequestHandler
{

    public void handle(IntelligentMattressProtocol protocol)
    {
    	

        DevUpdataReqParser parser = new DevUpdataReqParser();
        parser.parse(protocol);
        
        


    }

}
