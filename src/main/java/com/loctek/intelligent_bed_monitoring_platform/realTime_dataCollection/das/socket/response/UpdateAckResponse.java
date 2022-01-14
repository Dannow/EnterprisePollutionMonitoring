package com.loctek.intelligent_bed_monitoring_platform.realTime_dataCollection.das.socket.response;


import com.loctek.intelligent_bed_monitoring_platform.realTime_dataCollection.das.socket.channel.ChannelMap;
import com.loctek.intelligent_bed_monitoring_platform.realTime_dataCollection.das.socket.handler.DataPackageConstants;
import com.loctek.intelligent_bed_monitoring_platform.realTime_dataCollection.das.socket.request.BaseRequest;
import com.loctek.intelligent_bed_monitoring_platform.realTime_dataCollection.das.socket.server.DeviceMagager;

public class UpdateAckResponse extends SocketResponse {

	
	
	public UpdateAckResponse(BaseRequest req)
	{
		commandCode = DataPackageConstants.SER_UPGRADE_ACK;
		request = req;
		sn = req.getSn();
		
		DeviceMagager device = ChannelMap.getDeviceBySn(sn);
		
		
		

        byte[] updateInfoBytes = new byte[10];
       
        
        commandParams = updateInfoBytes;
	}
}
