package com.loctek.intelligent_bed_monitoring_platform.realTime_dataCollection.das.socket.parser;


import com.loctek.intelligent_bed_monitoring_platform.realTime_dataCollection.das.socket.channel.ChannelMap;
import com.loctek.intelligent_bed_monitoring_platform.realTime_dataCollection.das.socket.handler.DataPackageConstants;
import com.loctek.intelligent_bed_monitoring_platform.realTime_dataCollection.das.socket.request.UpdateDataInfoAckRequest;
import com.loctek.intelligent_bed_monitoring_platform.realTime_dataCollection.das.socket.server.DeviceMagager;
import com.loctek.intelligent_bed_monitoring_platform.realTime_dataCollection.das.socket.server.IntelligentMattressProtocol;
import com.loctek.intelligent_bed_monitoring_platform.realTime_dataCollection.das.socket.update.CocciBin;

public class UpdateDataInfoAckParser  extends BaseRequestParser
{

    @Override
    public UpdateDataInfoAckRequest parse(IntelligentMattressProtocol protocol)
    {
        byte[] bytes = protocol.getContent();
        UpdateDataInfoAckRequest request = new UpdateDataInfoAckRequest();
        super.parse(protocol, request);
        
 
        int dataLen = (bytes[DataPackageConstants.COMMAND_CODE_INDEX + 1] & 0xff) << 8;
        dataLen += bytes[DataPackageConstants.COMMAND_CODE_INDEX + 2] & 0xff;
//        log.error("dataLen = " + dataLen);
        
        request.setDataLen(dataLen);
        
        
        DeviceMagager device = ChannelMap.getDeviceBySn(protocol.getSn());
        CocciBin bin  = device.getBin();
        bin.setPacket_size(dataLen);
		
		
        
        return request;
    }

}
