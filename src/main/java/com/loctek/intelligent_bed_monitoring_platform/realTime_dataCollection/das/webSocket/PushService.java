package com.loctek.intelligent_bed_monitoring_platform.realTime_dataCollection.das.webSocket;

import com.loctek.intelligent_bed_monitoring_platform.common_model.domain.permission_management.entity.User;
import io.netty.channel.Channel;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;

import java.util.concurrent.ConcurrentHashMap;

public class PushService {
    /**
     * 推送给指定用户
     */

    public static void pushMsgToOne(String userID, Object msg) {
        // 获得存放用户与channel的对应信息的Map
        ConcurrentHashMap<String, Channel> userChannelMap = NettyConfig.getUserChannelMap();
        // 根据userID获得对应的channel
        Channel channel_Web = userChannelMap.get(userID+"_Web");
        Channel channel_Android = userChannelMap.get(userID+"_Android");
        // 如果有对应的channel（既可以用户在线），则直接发送给他
        if (channel_Web != null){
            channel_Web.writeAndFlush(new TextWebSocketFrame((String)msg));
        }else {
            System.out.println("===无Web端连接");
        }
        if (channel_Android != null){
            channel_Android.writeAndFlush(new TextWebSocketFrame((String)msg));
        }else {
            System.out.println("===无安卓端连接");
        }

    }
    /**
     * 推送给所有用户
     */
    public static void  pushMsgToAll(String msg) {
        NettyConfig.getChannelGroup().writeAndFlush(new TextWebSocketFrame(msg));
    }
}
