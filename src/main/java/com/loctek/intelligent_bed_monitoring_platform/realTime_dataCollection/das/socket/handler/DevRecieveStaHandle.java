package com.loctek.intelligent_bed_monitoring_platform.realTime_dataCollection.das.socket.handler;


import com.loctek.intelligent_bed_monitoring_platform.realTime_dataCollection.das.socket.server.IntelligentMattressProtocol;

public class DevRecieveStaHandle implements RequestHandler {

	public void handle(IntelligentMattressProtocol protocol) {
		// TODO Auto-generated method stub

//		DevRecieveStaRequest request = new DevRecieveStaRequest();
//
//	        // 如果时间相差1分钟，则开始校时
////	        long minusSeconds = request.getTime() * 1000L - new Date().getTime();
////	        if (minusSeconds < 10 * 1000 && minusSeconds > -10 * 1000) return;
//	        // 返回时间an
//	        Channel channel = ChannelMap.getChannelBySn(request.getSn());
//	        
//	        
//	        byte[] freqBytes = new byte[1];
//	        freqBytes[0] = (byte)60;
//	        SocketResponse response = new SocketResponse(request, DataPackageConstants.SER_SET_AUTODEVICESTATUS, freqBytes);
//	        
//	        channel.write(response.getResponse());
	        
//	        //应答后再发送一个获取设备信息的命令
//	        SocketResponse getDeviceInfoCmd = new SocketResponse(request, DataPackageConstants.SER_DEVICE_INFO, new byte[0]);
//	        
//	        byte[] deviceInfoCmdBytes = getDeviceInfoCmd.getResponse();
//	        channel.writeAndFlush(deviceInfoCmdBytes);
	}

}
