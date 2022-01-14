package com.loctek.intelligent_bed_monitoring_platform.realTime_dataCollection.das.socket.handler;


import com.loctek.intelligent_bed_monitoring_platform.realTime_dataCollection.das.socket.parser.DevSurroundingsInfoParser;
import com.loctek.intelligent_bed_monitoring_platform.realTime_dataCollection.das.socket.request.DevSurroundingsInfoRequest;
import com.loctek.intelligent_bed_monitoring_platform.realTime_dataCollection.das.socket.server.IntelligentMattressProtocol;

public class DevSurroundingsInfoHandler implements RequestHandler{

	@Override
	public void handle(IntelligentMattressProtocol protocol) {
		// TODO Auto-generated method stub

		DevSurroundingsInfoParser parser = new DevSurroundingsInfoParser();
		DevSurroundingsInfoRequest request = parser.parse(protocol);
        
        
	}

}
