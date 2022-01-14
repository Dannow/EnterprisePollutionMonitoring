package com.loctek.intelligent_bed_monitoring_platform.realTime_dataCollection.das.socket.handler;


import com.loctek.intelligent_bed_monitoring_platform.realTime_dataCollection.das.socket.parser.DevGsmGpsInfoParser;
import com.loctek.intelligent_bed_monitoring_platform.realTime_dataCollection.das.socket.request.DevGsmGpsInfoRequest;
import com.loctek.intelligent_bed_monitoring_platform.realTime_dataCollection.das.socket.server.IntelligentMattressProtocol;

public class DevGsmGpsInfoHandler  implements RequestHandler{

	@Override
	public void handle(IntelligentMattressProtocol protocol) {
		// TODO Auto-generated method stub

        DevGsmGpsInfoParser parser = new DevGsmGpsInfoParser();
        DevGsmGpsInfoRequest request = parser.parse(protocol);
	}

}
