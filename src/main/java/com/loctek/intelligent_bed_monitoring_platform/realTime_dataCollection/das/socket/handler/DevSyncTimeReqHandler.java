package com.loctek.intelligent_bed_monitoring_platform.realTime_dataCollection.das.socket.handler;



import com.loctek.intelligent_bed_monitoring_platform.realTime_dataCollection.das.socket.channel.ChannelMap;
import com.loctek.intelligent_bed_monitoring_platform.realTime_dataCollection.das.socket.parser.DevSyncTimeReqParser;
import com.loctek.intelligent_bed_monitoring_platform.realTime_dataCollection.das.socket.request.DevSyncTimeReqRequest;
import com.loctek.intelligent_bed_monitoring_platform.realTime_dataCollection.das.socket.response.SocketResponse;
import com.loctek.intelligent_bed_monitoring_platform.realTime_dataCollection.das.socket.response.SyncTimeResponse;
import com.loctek.intelligent_bed_monitoring_platform.realTime_dataCollection.das.socket.server.IntelligentMattressProtocol;
import io.netty.channel.Channel;


/**
 * 同步时间的处理类 DevSyncTimeReqHandler xieyonggao 2018年5月31日 下午1:42:38
 * 
 * @version 1.0.0
 */
public class DevSyncTimeReqHandler extends BaseRequestHandler
{

    public void handle(IntelligentMattressProtocol protocol)
    {
        DevSyncTimeReqParser parser = new DevSyncTimeReqParser();
        DevSyncTimeReqRequest request = parser.parse(protocol);

        // 如果时间相差1分钟，则开始校时
        Channel channel = ChannelMap.getChannelBySn(request.getSn());
        
      
        SocketResponse response = new SyncTimeResponse(request);
        
        channel.write(response.getResponse());
        
        //应答后再发送一个获取设备信息的命令
//        SocketResponse getDeviceInfoCmd = new SocketResponse(request, DataPackageConstants.SER_DEVICE_INFO, new byte[0]);
//        byte[] deviceInfoCmdBytes = getDeviceInfoCmd.getResponse();
//        channel.writeAndFlush(deviceInfoCmdBytes);
        

        super.handle(protocol);
    }

}
