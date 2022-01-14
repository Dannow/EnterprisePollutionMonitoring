package com.loctek.intelligent_bed_monitoring_platform.realTime_dataCollection.das.socket.parser;



import com.loctek.intelligent_bed_monitoring_platform.realTime_dataCollection.dao.DevDeviceInfoDao;
import com.loctek.intelligent_bed_monitoring_platform.realTime_dataCollection.das.socket.handler.DataPackageConstants;
import com.loctek.intelligent_bed_monitoring_platform.realTime_dataCollection.das.socket.request.DevDeviceInfoRequest;
import com.loctek.intelligent_bed_monitoring_platform.realTime_dataCollection.das.socket.server.IntelligentMattressProtocol;
import com.loctek.intelligent_bed_monitoring_platform.realTime_dataCollection.das.util.ByteUtils;

import java.sql.SQLException;

public class DevDeviceInfoParser extends BaseRequestParser {

	@Override
	public DevDeviceInfoRequest parse(IntelligentMattressProtocol protocol) {
		byte[] bytes = protocol.getContent();
		DevDeviceInfoRequest request = new DevDeviceInfoRequest();
		super.parse(protocol, request);

		request.setFwVersion(ByteUtils.bytes2char2String(bytes, DataPackageConstants.COMMAND_CODE_INDEX + 1, 8));
	
//		log.debug("getFwVersion" + request.getFwVersion());
		
		
		request.setAlgVersion(ByteUtils.bytes2char2String(bytes, DataPackageConstants.COMMAND_CODE_INDEX + 9, 8));
		request.setBootVersion(ByteUtils.bytes2char2String(bytes, DataPackageConstants.COMMAND_CODE_INDEX + 17, 8));
		request.setUpgradeFlag(bytes[DataPackageConstants.COMMAND_CODE_INDEX + 35]);
		
		int SocketA_Using_Len = bytes[DataPackageConstants.COMMAND_CODE_INDEX + 88] & 0xff;
		request.setSocketAUsingIp(ByteUtils.bytes2char2String(bytes, DataPackageConstants.COMMAND_CODE_INDEX + 36, SocketA_Using_Len));
		
		short socketAUsingPort = (short)((bytes[DataPackageConstants.COMMAND_CODE_INDEX + 86] & 0xff) * 256 + (bytes[DataPackageConstants.COMMAND_CODE_INDEX + 87] & 0xff));
		request.setSocketAUsingPort(socketAUsingPort);
		
		
		
		
		int SocketA_Len  = bytes[DataPackageConstants.COMMAND_CODE_INDEX + 141] & 0xff;
		request.setSocketAIp(ByteUtils.bytes2char2String(bytes, DataPackageConstants.COMMAND_CODE_INDEX + 89, SocketA_Len));
		
		short SocketA_Port = (short)((bytes[DataPackageConstants.COMMAND_CODE_INDEX + 139] & 0xff) * 256 + (bytes[DataPackageConstants.COMMAND_CODE_INDEX + 140] & 0xff));
		request.setSocketAPort(SocketA_Port);
		
		
		
		

		request.setMac(ByteUtils.bytes2char2String(bytes, DataPackageConstants.COMMAND_CODE_INDEX + 142, 12));

//		log.debug("getMac=" + request.getMac());
		
		
		
		request.setCalibration(bytes[DataPackageConstants.COMMAND_CODE_INDEX + 154]);

		// log.debug("DevDeviceInfoRequest setDeviceEnable");
		request.setDeviceEnable(bytes[DataPackageConstants.COMMAND_CODE_INDEX + 155]);
		
		request.setRawDataEnable(bytes[DataPackageConstants.COMMAND_CODE_INDEX + 156]);
		request.setBodySignFreq(bytes[DataPackageConstants.COMMAND_CODE_INDEX + 157]);

		byte netEable = bytes[DataPackageConstants.COMMAND_CODE_INDEX + 158];
		
		byte netEableEthernet = 0;
		byte netEableMobile = 0;
		byte netEableWifi = 0;

		if((netEable & 0x01) != 0)
		{
			netEableWifi = 1;
		}
		
		if((netEable & (0x01<<1)) != 0)
		{
			netEableMobile = 1;
		}
		
		if((netEable & (0x01<<2)) != 0)
		{
			netEableEthernet = 1;
		}
		request.setNetEableEthernet(netEableEthernet);

		// log.debug("DevDeviceInfoRequest setNetEableMobile");
		request.setNetEableMobile(netEableMobile);

		// log.debug("DevDeviceInfoRequest setNetEableWifi");
		request.setNetEableWifi(netEableWifi);



		request.setAutoCalEnable(bytes[DataPackageConstants.COMMAND_CODE_INDEX + 159]);

		request.setOfflineDataEnable(bytes[DataPackageConstants.COMMAND_CODE_INDEX + 160]);

		request.setLogDataEnable(bytes[DataPackageConstants.COMMAND_CODE_INDEX + 161]);

		DevDeviceInfoDao dao = new DevDeviceInfoDao();
		try {
			dao.update(request);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return request;
	}

}
