package com.loctek.intelligent_bed_monitoring_platform.realTime_dataCollection.das.socket.response;


import com.loctek.intelligent_bed_monitoring_platform.realTime_dataCollection.das.socket.handler.DataPackageConstants;
import com.loctek.intelligent_bed_monitoring_platform.realTime_dataCollection.das.socket.request.BaseRequest;

import java.util.Date;

public class SyncTimeResponse extends SocketResponse {

	
	
	public SyncTimeResponse(BaseRequest req)
	{
		commandCode = DataPackageConstants.SER_SYNC_TIME_ACK;
		request = req;
		sn = req.getSn();
		
		Long time = new Date().getTime() / 1000;
		time += 8*60*60;
        String str = Long.toHexString(time);
        System.out.println("time = " + time);
  
        byte[] timeBytes = new byte[8];
        for(int i=0;i<8;i++)
        {
        	timeBytes[i] = (byte) ((time >> (8-1-i)*8) & 0xff);
        }
        commandParams = timeBytes;
        
	}
}
