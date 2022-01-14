package com.loctek.intelligent_bed_monitoring_platform.realTime_dataCollection.das.socket.channel;

import com.loctek.intelligent_bed_monitoring_platform.realTime_dataCollection.das.socket.server.DeviceMagager;
import io.netty.channel.Channel;

import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

public class ChannelMap
{
    private static Map<String, Channel> channelMap = new ConcurrentHashMap<>();
    
    private static Map<String, DeviceMagager> deviceMap = new ConcurrentHashMap<>();
    
//    private static Map<String, String> sn2ChannelIdMap = new ConcurrentHashMap<>();
    
//    public static Channel getChannelById(String channelId){
//        if(channelMap==null||channelMap.isEmpty()){
//            return null;
//        }
//        return channelMap.get(channelId);
//    }
    
    public static Channel getChannelBySn(String sn){
        if(channelMap==null||channelMap.isEmpty()){
            return null;
        }
        return channelMap.get(sn);
        
        
    }

    public static DeviceMagager getDeviceBySn(String sn){
        if(channelMap==null||channelMap.isEmpty()){
            return null;
        }
        return deviceMap.get(sn);
        
        
    }
//    public static void addChannel(String channelId,Channel channel){
//        if(channelMap==null){
//            channelMap=new ConcurrentHashMap<>();
//        }
//        channelMap.put(channelId,channel);
//    }
    
    public static int removeChannel(String channelId){
        for (Entry<String, Channel> entry : channelMap.entrySet())
        {
            if (entry.getValue() == null) continue;
            if (entry.getValue().id().asShortText().equals(channelId) ) {
                channelMap.remove(entry.getKey());
                return 0;
            }
        }
        return 1;
    }
    
    public static void addSn(String sn,Channel channel, DeviceMagager device) {
        if(channelMap == null){
            channelMap = new ConcurrentHashMap<>();
        }
        channelMap.put(sn,channel);
        
        if(deviceMap == null){
        	deviceMap = new ConcurrentHashMap<>();
        }
        deviceMap.put(sn,device);
    }
}
