package com.loctek.intelligent_bed_monitoring_platform.realTime_dataCollection.das.socket.handler;


import com.loctek.intelligent_bed_monitoring_platform.realTime_dataCollection.das.socket.server.IntelligentMattressProtocol;

/**
 * socket请求的处理类
 * RequestHandler
 * 
 * xieyonggao
 * xieyonggao
 * 2018年5月29日 下午5:03:15
 * 
 * @version 1.0.0
 *
 */
public interface RequestHandler
{
    public void handle(IntelligentMattressProtocol protocol);
}
