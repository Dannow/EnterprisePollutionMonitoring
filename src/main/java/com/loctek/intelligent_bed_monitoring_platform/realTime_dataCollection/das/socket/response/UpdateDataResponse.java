package com.loctek.intelligent_bed_monitoring_platform.realTime_dataCollection.das.socket.response;


import com.loctek.intelligent_bed_monitoring_platform.realTime_dataCollection.das.socket.channel.ChannelMap;
import com.loctek.intelligent_bed_monitoring_platform.realTime_dataCollection.das.socket.handler.DataPackageConstants;
import com.loctek.intelligent_bed_monitoring_platform.realTime_dataCollection.das.socket.request.BaseRequest;
import com.loctek.intelligent_bed_monitoring_platform.realTime_dataCollection.das.socket.server.DeviceMagager;
import com.loctek.intelligent_bed_monitoring_platform.realTime_dataCollection.utils.log;

public class UpdateDataResponse extends SocketResponse {


	private static String classname = UpdateDataResponse.class.getSimpleName();
	
	public UpdateDataResponse(BaseRequest req)
	{
		commandCode = DataPackageConstants.SER_UPGRADE_DATA;
		request = req;
		sn = req.getSn();
		
		DeviceMagager device = ChannelMap.getDeviceBySn(sn);
		
		
		
		int packet_size = device.getBin().getPacket_size();
        byte[] updateDataBytes = new byte[packet_size+2];
        int packetID = (int) device.getBin().getPacketId();
        
        updateDataBytes[0] = (byte)((packetID >> 8) & 0xff);
        updateDataBytes[1] = (byte)((packetID >> 0) & 0xff);
        log.debug(classname, "UpdateDataResponse packet_size = " + packet_size);

        
        
        
        byte []imgData = device.getBin().getData();
        int len = (int) device.getBin().getImg_size();
        len = len - packetID*packet_size;
        if( len > packet_size)
        {
        	len = packet_size;
        }
        System.arraycopy(imgData, packetID*packet_size,updateDataBytes, 2,len);
        
        
        commandParams = updateDataBytes;
	}
	
}