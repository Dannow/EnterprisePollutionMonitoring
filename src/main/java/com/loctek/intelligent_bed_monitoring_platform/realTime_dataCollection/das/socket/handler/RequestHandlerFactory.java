package com.loctek.intelligent_bed_monitoring_platform.realTime_dataCollection.das.socket.handler;

import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

@Slf4j
public class RequestHandlerFactory {
	private static Map<Integer, String> INIT_HANDLER_CLASS_MAP = new HashMap<Integer, String>();

	private static Map<Integer, RequestHandler> HANDLER_MAP = new HashMap<Integer, RequestHandler>();

	static {
		INIT_HANDLER_CLASS_MAP.put(DataPackageConstants.DEV_DEVICE_INFO,
				"com.loctek.intelligent_bed_monitoring_platform.realTime_dataCollection.das.socket.handler.DevDeviceInfoHandler");// 1设备基本信息
		INIT_HANDLER_CLASS_MAP.put(DataPackageConstants.DEV_DEVICE_STATUS,
				"com.loctek.intelligent_bed_monitoring_platform.realTime_dataCollection.das.socket.handler.DevDeviceStatusHandler");// 2设备当前状态
		INIT_HANDLER_CLASS_MAP.put(DataPackageConstants.DEV_SYNC_TIME_REQ,
				"com.loctek.intelligent_bed_monitoring_platform.realTime_dataCollection.das.socket.handler.DevSyncTimeReqHandler");// 3时间同步请求
		INIT_HANDLER_CLASS_MAP.put(DataPackageConstants.DEV_BODY_SIG, "com.loctek.intelligent_bed_monitoring_platform.realTime_dataCollection.das.socket.handler.DevBodySignHandler");// 4实时体征信息
 		INIT_HANDLER_CLASS_MAP.put(DataPackageConstants.DEV_CMD_RECEIVE_STA, "com.loctek.intelligent_bed_monitoring_platform.realTime_dataCollection.das.socket.handler.DevRecieveStaHandle");
        
 		INIT_HANDLER_CLASS_MAP.put(DataPackageConstants.DEV_2G_APGS_INFO, "com.loctek.intelligent_bed_monitoring_platform.realTime_dataCollection.das.socket.handler.DevGsmGpsInfoHandler");
 		INIT_HANDLER_CLASS_MAP.put(DataPackageConstants.DEV_SURRONGDINGS_INFO, "com.loctek.intelligent_bed_monitoring_platform.realTime_dataCollection.das.socket.handler.DevSurroundingsInfoHandler");

 		//请求升级数据
 		INIT_HANDLER_CLASS_MAP.put(DataPackageConstants.DEV_UPGRADE_ACK, "com.loctek.intelligent_bed_monitoring_platform.realTime_dataCollection.das.socket.handler.UpdataDataHandler");
 		
 		//
 		INIT_HANDLER_CLASS_MAP.put(DataPackageConstants.DEV_UPGRADE_DATAINFO_ACK, "com.loctek.intelligent_bed_monitoring_platform.realTime_dataCollection.das.socket.handler.UpdateDataInfoAckHandler");
 		

 		
 		
 		
        

		// 当前版本不可用
//        INIT_PARSER_CLASS_MAP.put(5, "DEV_RAW_DATA");

		INIT_HANDLER_CLASS_MAP.put(DataPackageConstants.DEV_BED_STATUS,
				"com.loctek.intelligent_bed_monitoring_platform.realTime_dataCollection.das.socket.handler.DevBedStatusHandler");// 6在床离床状态变化

		INIT_HANDLER_CLASS_MAP.put(DataPackageConstants.DEV_UPGRADE_ACK,
				"com.loctek.intelligent_bed_monitoring_platform.realTime_dataCollection.das.socket.handler.DevUpgradeAckHandler");// 9响应升级命令
	}

	public static RequestHandler getHandler(byte flag) {
		if (HANDLER_MAP.size() == 0) {
			for (Entry<Integer, String> entry : INIT_HANDLER_CLASS_MAP.entrySet()) {
				try {
					HANDLER_MAP.put(entry.getKey(), (RequestHandler) Class.forName(entry.getValue()).newInstance());
				} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
					//log.error("RequestParser getParser error.", e);
					throw new RuntimeException(e);
				}
			}
		}
		return HANDLER_MAP.get((int) flag);
	}

}
