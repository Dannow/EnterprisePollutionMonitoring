package com.loctek.intelligent_bed_monitoring_platform.realTime_dataCollection.das.socket.handler;


import com.alibaba.fastjson.JSON;
import com.loctek.intelligent_bed_monitoring_platform.realTime_dataCollection.dao.DevDeviceInfoDao;
import com.loctek.intelligent_bed_monitoring_platform.realTime_dataCollection.das.socket.parser.DevBodySignParser;
import com.loctek.intelligent_bed_monitoring_platform.realTime_dataCollection.das.socket.request.DevBodySignRequest;
import com.loctek.intelligent_bed_monitoring_platform.realTime_dataCollection.das.socket.server.IntelligentMattressProtocol;
import com.loctek.intelligent_bed_monitoring_platform.realTime_dataCollection.das.webSocket.PushService;

import java.sql.SQLException;

/**
 * 体征信息处理类 DevBodySignHandler xieyonggao 2018年5月31日 上午11:29:33
 * 
 * @version 1.0.0
 */
public class DevBodySignHandler extends BaseRequestHandler
{

    public void handle(IntelligentMattressProtocol protocol)
    {
        DevBodySignParser parser = new DevBodySignParser();
        DevBodySignRequest request = parser.parse(protocol);

        // 通过webSocket传输
        String msg = JSON.toJSONString(request);
//        PushService.pushMsgToAll(msg);
        DevDeviceInfoDao devDeviceInfoDao = new DevDeviceInfoDao();
        try {
            int deviceUser = devDeviceInfoDao.getDeviceUser(request.getSn());
            PushService.pushMsgToOne(Integer.toString(deviceUser),msg);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


//        byte[] freq = new byte[1];
//        freq[0] = 30;
//
//        SocketResponse response = new SocketResponse(request, DataPackageConstants.SER_SET_AUTODEVICESTATUS, freq);
//
//
//        Channel channel = ChannelMap.getChannelBySn(request.getSn());
//        channel.write(response.getResponse());
        // 调用父类BaseRequestHandler的handle
        super.handle(protocol);

    }

}
