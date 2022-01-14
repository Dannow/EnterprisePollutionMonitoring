package com.loctek.intelligent_bed_monitoring_platform.realTime_dataCollection.das.socket.handler;


import com.loctek.intelligent_bed_monitoring_platform.realTime_dataCollection.das.socket.channel.ChannelMap;
import com.loctek.intelligent_bed_monitoring_platform.realTime_dataCollection.das.socket.parser.DevBedStatusParser;
import com.loctek.intelligent_bed_monitoring_platform.realTime_dataCollection.das.socket.request.DevBedStatusRequest;
import com.loctek.intelligent_bed_monitoring_platform.realTime_dataCollection.das.socket.server.IntelligentMattressProtocol;
import io.netty.channel.Channel;


/**
 * DevBedStatusHandler xieyonggao 2018年5月30日 下午2:36:08
 * 
 * @version 1.0.0
 */
public class DevUpgradeAckHandler implements RequestHandler
{
    public void handle(IntelligentMattressProtocol protocol)
    {
        DevBedStatusParser parser = new DevBedStatusParser();
        DevBedStatusRequest request = parser.parse(protocol);

        String sn = request.getSn();
        
        Channel channel = ChannelMap.getChannelBySn(sn);
        
//        SocketResponse upgradeCmd = new SocketResponse(request, DataPackageConstants.SER_UPGRADE_DATA, new byte[0]);
//        byte[] upgradeCmdBytes = upgradeCmd.getResponse();
//        channel.writeAndFlush(upgradeCmdBytes);
    }


}
