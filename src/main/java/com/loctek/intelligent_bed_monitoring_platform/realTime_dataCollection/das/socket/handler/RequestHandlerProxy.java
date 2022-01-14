package com.loctek.intelligent_bed_monitoring_platform.realTime_dataCollection.das.socket.handler;

import com.loctek.intelligent_bed_monitoring_platform.realTime_dataCollection.dao.DevLogDao;
import com.loctek.intelligent_bed_monitoring_platform.realTime_dataCollection.das.socket.server.IntelligentMattressProtocol;
import lombok.extern.slf4j.Slf4j;

import java.sql.SQLException;

/**
 * 请求执行的代理类
 * RequestHandlerProxy
 * 
 * xieyonggao
 * xieyonggao
 * 2018年5月30日 上午9:30:03
 * 
 * @version 1.0.0
 *
 */
@Slf4j
public class RequestHandlerProxy
{
    public static void handle(IntelligentMattressProtocol protocol) {

    	DevLogDao dao = new DevLogDao();
        try {
			dao.addLog(protocol);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        RequestHandler handler = RequestHandlerFactory.getHandler(protocol.getCommandCode());
        if (protocol.getCommandCode() == 0) {
            throw new RuntimeException("不支持该操作代码.") ;
        }
        //log.info("Handler:{}",new Object[] {protocol.getCommandCode()});
        handler.handle(protocol);
    }

}
