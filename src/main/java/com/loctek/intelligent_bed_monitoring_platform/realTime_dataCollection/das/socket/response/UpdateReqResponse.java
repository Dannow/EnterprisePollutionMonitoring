package com.loctek.intelligent_bed_monitoring_platform.realTime_dataCollection.das.socket.response;


import com.loctek.intelligent_bed_monitoring_platform.realTime_dataCollection.das.socket.channel.ChannelMap;
import com.loctek.intelligent_bed_monitoring_platform.realTime_dataCollection.das.socket.handler.DataPackageConstants;
import com.loctek.intelligent_bed_monitoring_platform.realTime_dataCollection.das.socket.request.BaseRequest;
import com.loctek.intelligent_bed_monitoring_platform.realTime_dataCollection.das.socket.server.DeviceMagager;

public class UpdateReqResponse extends SocketResponse {

	
	
	public UpdateReqResponse(BaseRequest req)
	{
		commandCode = DataPackageConstants.SER_UPGRADE_REQ;
		request = req;
		sn = req.getSn();
		
		DeviceMagager device = ChannelMap.getDeviceBySn(sn);
		
		
		

        byte[] updateInfoBytes = new byte[10];
        int fw_len = (int) device.getBin().getImg_size();
        
        for(int i=0;i<4;i++)
        {
        	updateInfoBytes[0+i] = (byte)(( fw_len>>(3-i)*8) &0xff);
        }
        
        int version = (int) device.getBin().getVersion();
        
        for(int i=0;i<4;i++)
        {
        	updateInfoBytes[4+i] = (byte)(( version>>(3-i)*8) &0xff);
        }
        
        int type = (int) device.getBin().getFwType();
        
        updateInfoBytes[8] = (byte) type;

        updateInfoBytes[9] = device.getBin().getBBCcheck();
        
        commandParams = updateInfoBytes;
	}
	
}
