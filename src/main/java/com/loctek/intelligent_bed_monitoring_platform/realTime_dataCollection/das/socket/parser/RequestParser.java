package com.loctek.intelligent_bed_monitoring_platform.realTime_dataCollection.das.socket.parser;


import com.loctek.intelligent_bed_monitoring_platform.realTime_dataCollection.das.socket.request.BaseRequest;
import com.loctek.intelligent_bed_monitoring_platform.realTime_dataCollection.das.socket.server.IntelligentMattressProtocol;

/**
 * socket请求的解析基类
 * RequestParser
 * 
 * xieyonggao
 * xieyonggao
 * 2018年5月29日 下午4:56:19
 * 
 * @version 1.0.0
 *
 */
public interface RequestParser
{
    public BaseRequest parse(IntelligentMattressProtocol protocol);
}
