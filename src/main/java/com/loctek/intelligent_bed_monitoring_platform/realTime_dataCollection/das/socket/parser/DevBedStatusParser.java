package com.loctek.intelligent_bed_monitoring_platform.realTime_dataCollection.das.socket.parser;


import com.loctek.intelligent_bed_monitoring_platform.realTime_dataCollection.das.socket.handler.DataPackageConstants;
import com.loctek.intelligent_bed_monitoring_platform.realTime_dataCollection.das.socket.request.DevBedStatusRequest;
import com.loctek.intelligent_bed_monitoring_platform.realTime_dataCollection.das.socket.server.IntelligentMattressProtocol;
import com.loctek.intelligent_bed_monitoring_platform.realTime_dataCollection.das.util.ByteUtils;

public class DevBedStatusParser extends BaseRequestParser
{

    @Override
    public DevBedStatusRequest parse(IntelligentMattressProtocol protocol)
    {
        byte[] bytes = protocol.getContent();
        DevBedStatusRequest request = new DevBedStatusRequest();
        super.parse(protocol, request);
        request.setStatus(bytes[DataPackageConstants.COMMAND_CODE_INDEX + 9]);
        String hexTime = ByteUtils.bytesToHexString(bytes, DataPackageConstants.COMMAND_CODE_INDEX + 1, 8);
        request.setTime(Long.parseLong(hexTime,16));
        return request;
    }
}
