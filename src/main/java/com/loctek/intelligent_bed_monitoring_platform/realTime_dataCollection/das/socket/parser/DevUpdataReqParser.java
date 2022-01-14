package com.loctek.intelligent_bed_monitoring_platform.realTime_dataCollection.das.socket.parser;


import com.loctek.intelligent_bed_monitoring_platform.realTime_dataCollection.das.socket.channel.ChannelMap;
import com.loctek.intelligent_bed_monitoring_platform.realTime_dataCollection.das.socket.handler.DataPackageConstants;
import com.loctek.intelligent_bed_monitoring_platform.realTime_dataCollection.das.socket.request.BaseRequest;
import com.loctek.intelligent_bed_monitoring_platform.realTime_dataCollection.das.socket.request.DevUpdateDataRequest;
import com.loctek.intelligent_bed_monitoring_platform.realTime_dataCollection.das.socket.response.SocketResponse;
import com.loctek.intelligent_bed_monitoring_platform.realTime_dataCollection.das.socket.response.UpdateDataResponse;
import com.loctek.intelligent_bed_monitoring_platform.realTime_dataCollection.das.socket.server.DeviceMagager;
import com.loctek.intelligent_bed_monitoring_platform.realTime_dataCollection.das.socket.server.IntelligentMattressProtocol;
import com.loctek.intelligent_bed_monitoring_platform.realTime_dataCollection.das.socket.update.CocciBin;
import com.loctek.intelligent_bed_monitoring_platform.realTime_dataCollection.utils.log;
import io.netty.channel.Channel;


public class DevUpdataReqParser extends BaseRequestParser
{

	private static String classname = DevUpdataReqParser.class.getSimpleName();
    @Override
    public DevUpdateDataRequest parse(IntelligentMattressProtocol protocol)
    {
        byte[] bytes = protocol.getContent();
        DevUpdateDataRequest request = new DevUpdateDataRequest();
        super.parse(protocol, request);
        
        int status = bytes[DataPackageConstants.COMMAND_CODE_INDEX + 1] & 0xff;
        log.debug(classname, protocol.getSn()+"  status = " + status);
        int packetId = (bytes[DataPackageConstants.COMMAND_CODE_INDEX + 2] & 0xff) << 8;
        packetId += bytes[DataPackageConstants.COMMAND_CODE_INDEX + 3] & 0xff;
        log.debug(classname, protocol.getSn()+ "  packetId = " + packetId);
        
        DeviceMagager device = ChannelMap.getDeviceBySn(protocol.getSn());
        CocciBin bin = device.getBin();
        bin.setPacketId(packetId);
        request.setStatus((byte)status);
        request.setPacketId(packetId);
        
        bin.setErrReSendCount(0);
        
        
        switch (status)
        {
	        case 0: //START
	        	device.setUpdating(true);
	        	reseponseUpdate(request);
	        	break;
	        case 1: //END
	        	device.finishUpdate();
	        	break;
	        case 2: //ERR_RESEND
	        case 3: //OK
	        	reseponseUpdate(request);
	        	break;
	        
	        case 4://EXIT
	        	device.finishUpdate();
	        	break;
	        case 5:
	        	device.finishUpdate();
	        	break;
	        default:
        	
        }
        
        return request;
    }
    
    public  void reseponseUpdate(BaseRequest request)
    {
    	
    	DeviceMagager device = ChannelMap.getDeviceBySn(request.getSn());
        CocciBin bin = device.getBin();
        bin.setTimeLastRes(System.currentTimeMillis());
        
    	Channel channel = ChannelMap.getChannelBySn(request.getSn());
        
        
        SocketResponse response = new UpdateDataResponse(request);
        
        channel.write(response.getResponse());
//        channel.flush();
    }
}
