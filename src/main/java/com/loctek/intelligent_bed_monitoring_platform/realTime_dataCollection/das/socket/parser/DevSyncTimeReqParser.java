package com.loctek.intelligent_bed_monitoring_platform.realTime_dataCollection.das.socket.parser;


import com.loctek.intelligent_bed_monitoring_platform.realTime_dataCollection.dao.DevSyncTimeReqDao;
import com.loctek.intelligent_bed_monitoring_platform.realTime_dataCollection.das.socket.handler.DataPackageConstants;
import com.loctek.intelligent_bed_monitoring_platform.realTime_dataCollection.das.socket.request.DevSyncTimeReqRequest;
import com.loctek.intelligent_bed_monitoring_platform.realTime_dataCollection.das.socket.server.IntelligentMattressProtocol;
import com.loctek.intelligent_bed_monitoring_platform.realTime_dataCollection.das.util.ByteUtils;

import java.sql.SQLException;

public class DevSyncTimeReqParser extends BaseRequestParser {

	@Override
	public DevSyncTimeReqRequest parse(IntelligentMattressProtocol protocol) {
		// log.debug("DevSyncTimeReqRequest parse 1 +++++++++++++++++++++");
		byte[] bytes = protocol.getContent();
		DevSyncTimeReqRequest request = new DevSyncTimeReqRequest();
		super.parse(protocol, request);

		// log.debug("DevSyncTimeReqRequest parse 2 + bytes.length "+
		// bytes.length+ "++++++++++++++++++++");

		String hexTime = ByteUtils.bytesToHexString(bytes, DataPackageConstants.COMMAND_CODE_INDEX + 1, 8);
		// log.debug("DevSyncTimeReqRequest parse 3 +++++++++++++++++++++");

		request.setTime(Long.parseLong(hexTime, 16));

		DevSyncTimeReqDao dao = new DevSyncTimeReqDao();
		try {
			dao.insert(request);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return request;
	}

}
