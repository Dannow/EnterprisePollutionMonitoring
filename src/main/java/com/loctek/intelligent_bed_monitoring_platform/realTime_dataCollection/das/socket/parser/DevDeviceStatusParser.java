package com.loctek.intelligent_bed_monitoring_platform.realTime_dataCollection.das.socket.parser;


import com.loctek.intelligent_bed_monitoring_platform.realTime_dataCollection.dao.DevDeviceStatusDao;
import com.loctek.intelligent_bed_monitoring_platform.realTime_dataCollection.das.socket.handler.DataPackageConstants;
import com.loctek.intelligent_bed_monitoring_platform.realTime_dataCollection.das.socket.request.DevDeviceStatusRequest;
import com.loctek.intelligent_bed_monitoring_platform.realTime_dataCollection.das.socket.server.IntelligentMattressProtocol;

import java.sql.SQLException;

public class DevDeviceStatusParser extends BaseRequestParser {

	@Override
	public DevDeviceStatusRequest parse(IntelligentMattressProtocol protocol) {
		byte[] bytes = protocol.getContent();
		DevDeviceStatusRequest request = new DevDeviceStatusRequest();
		super.parse(protocol, request);
		request.setDeviceStatus(bytes[DataPackageConstants.COMMAND_CODE_INDEX + 1]);
		request.setConnectStatus(bytes[DataPackageConstants.COMMAND_CODE_INDEX + 2]);
		
//		log.debug("getDeviceStatus=" + request.getDeviceStatus());
//		log.debug("getConnectStatus=" + request.getConnectStatus());

		DevDeviceStatusDao dao = new DevDeviceStatusDao();
		try {
			dao.insert(request);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return request;
	}
}
