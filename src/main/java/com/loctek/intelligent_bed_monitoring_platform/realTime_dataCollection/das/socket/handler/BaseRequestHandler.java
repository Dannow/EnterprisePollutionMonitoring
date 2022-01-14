package com.loctek.intelligent_bed_monitoring_platform.realTime_dataCollection.das.socket.handler;

import com.loctek.intelligent_bed_monitoring_platform.realTime_dataCollection.das.socket.channel.ChannelMap;
import com.loctek.intelligent_bed_monitoring_platform.realTime_dataCollection.das.socket.parser.DevUpdataReqParser;
import com.loctek.intelligent_bed_monitoring_platform.realTime_dataCollection.das.socket.request.ServerRequest;
import com.loctek.intelligent_bed_monitoring_platform.realTime_dataCollection.das.socket.response.SocketResponse;
import com.loctek.intelligent_bed_monitoring_platform.realTime_dataCollection.das.socket.response.UpdateReqResponse;
import com.loctek.intelligent_bed_monitoring_platform.realTime_dataCollection.das.socket.server.DeviceMagager;
import com.loctek.intelligent_bed_monitoring_platform.realTime_dataCollection.das.socket.server.IntelligentMattressProtocol;
import com.loctek.intelligent_bed_monitoring_platform.realTime_dataCollection.das.socket.update.CocciBin;
import com.loctek.intelligent_bed_monitoring_platform.realTime_dataCollection.utils.log;
import io.netty.channel.Channel;



public class BaseRequestHandler implements RequestHandler{
	/**
	 * 用于设备更新的Handler
	 * @param protocol
	 */
	public void handle(IntelligentMattressProtocol protocol)
	{
		String sn = protocol.getSn();
		if(sn==null)
			return;
		DeviceMagager device = ChannelMap.getDeviceBySn(sn);
		if(device.isNeedUpdate() && device.isUpdating()==false)// 判断是否需要升级
		{
			ServerRequest request = protocol.getRequest();

	        Channel channel = ChannelMap.getChannelBySn(request.getSn());
	        
	        

	        
	        SocketResponse response = new UpdateReqResponse(request);
	        
	        log.error("send UpdateReqResponse");
	        
	        try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        
	        // 发送升级响应给设备
	        channel.write(response.getResponse());
	        
		}else if(device.isUpdating()==true)//升级中，判读是否超时，超时再发
		{
			CocciBin bin = device.getBin();
			long timeLastRes = bin.getTimeLastRes();
			if(timeLastRes!= 0 && timeLastRes< System.currentTimeMillis() - DataPackageConstants.UPDATE_TIME_OUT_SEC)
			{
				log.error("----------------------------------------------------------");
				log.error("---------------------UPDATE_TIME_OUT_SEC  sn=" +sn 
						+ "------------------");
				log.error("----------------------------------------------------------");
				DevUpdataReqParser para = new DevUpdataReqParser();
				ServerRequest request = protocol.getRequest();
				para.reseponseUpdate(request);
				
				bin.ErrCountIncrement();
				
				if(bin.getErrReSendCount()>20)
				{
					bin.setErrReSendCount(0);
					device.setUpdating(false);
				}
				
			}
		}
		
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}

}
