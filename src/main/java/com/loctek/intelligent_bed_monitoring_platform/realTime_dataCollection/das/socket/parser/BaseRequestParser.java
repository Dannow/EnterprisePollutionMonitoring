package com.loctek.intelligent_bed_monitoring_platform.realTime_dataCollection.das.socket.parser;


import com.loctek.intelligent_bed_monitoring_platform.realTime_dataCollection.das.socket.request.BaseRequest;
import com.loctek.intelligent_bed_monitoring_platform.realTime_dataCollection.das.socket.server.IntelligentMattressProtocol;

public abstract class BaseRequestParser implements RequestParser
{
    public void parse(IntelligentMattressProtocol protocol, BaseRequest request){
        request.setDataHeader(protocol.getCommandCode());
        request.setSn(protocol.getSn());
    }
}
