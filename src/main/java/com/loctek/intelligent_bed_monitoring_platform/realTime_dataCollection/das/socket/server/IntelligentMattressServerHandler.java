package com.loctek.intelligent_bed_monitoring_platform.realTime_dataCollection.das.socket.server;

import com.loctek.intelligent_bed_monitoring_platform.realTime_dataCollection.dao.ClassLogDao;
import com.loctek.intelligent_bed_monitoring_platform.realTime_dataCollection.dao.DevListDao;
import com.loctek.intelligent_bed_monitoring_platform.realTime_dataCollection.dao.DevUpdateDataIntevalDao;
import com.loctek.intelligent_bed_monitoring_platform.realTime_dataCollection.dao.UpdateMaxDevicesDao;
import com.loctek.intelligent_bed_monitoring_platform.realTime_dataCollection.das.socket.channel.ChannelMap;
import com.loctek.intelligent_bed_monitoring_platform.realTime_dataCollection.das.socket.handler.RequestHandlerProxy;
import com.loctek.intelligent_bed_monitoring_platform.realTime_dataCollection.das.util.ByteUtils;
import com.loctek.intelligent_bed_monitoring_platform.realTime_dataCollection.domain.Device;
import com.loctek.intelligent_bed_monitoring_platform.realTime_dataCollection.utils.ClassLog;
import com.loctek.intelligent_bed_monitoring_platform.realTime_dataCollection.utils.log;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.ArrayList;
import java.util.List;


public class IntelligentMattressServerHandler extends ChannelInboundHandlerAdapter {
	/*
	 * channelAction
	 *
	 * channel 通道 action 活跃的
	 *
	 * 当客户端主动链接服务端的链接后，这个通道就是活跃的了。也就是客户端与服务端建立了通信通道并且可以传输数据
	 *
	 */

	private static String classname = IntelligentMattressServerHandler.class.getSimpleName();
	private String sn = null;
	private int connectCount = 0;
	
	
	static Thread mthread = null;
	
	static List<Device> mDeviceList=new ArrayList<>();
	List<Device> oldDeviceList  =new ArrayList<>();
	
	
	boolean isDeviceExist(String value)
	{
		boolean ret = false;
		for(int i=0;i<mDeviceList.size();i++)
		{
			Device mDevice = mDeviceList.get(i);
			if(value.equals(mDevice.getSn()))
			{
				ret = true; 
				break;
			}
		}
		
		return ret;
	}
	
	boolean setDeviceOnline(String mSn)
	{
		for(int i=0;i<mDeviceList.size();i++)
		{
			Device mDevice = mDeviceList.get(i);
			if(mSn.equals(mDevice.getSn()))
			{
				mDevice.setOnline(1);
				log.debug(classname, "setDeviceOnline "+sn);
				break;
			}
		}
		return true;
	}
	
	static int count = 0;
	class CheckOnlineThread extends Thread
	{
		@Override
		public void run() {
			// TODO Auto-generated method stub
			super.run();
		
			

			DevListDao dao = new DevListDao();
			
			mDeviceList = dao.getDeviceList();
			
			DevUpdateDataIntevalDao dao_time = new DevUpdateDataIntevalDao();
			
			UpdateMaxDevicesDao dao_max = new UpdateMaxDevicesDao();
			
			ClassLogDao dao_log = new ClassLogDao();
			
			
			while(true)
			{
				
				log.debug(classname, "CheckOnlineThread " + count++);
				
				
				int maxDevices = dao_max.getUpdateMaxDevices();
				if(maxDevices != PackageDecoder.UpdateMaxDevices)
				{
					PackageDecoder.setUpdateMaxDevices(maxDevices);
				}
				
				
				List<ClassLog> mClassLogList = dao_log.getClassLog();
				for(int i=0;i<mClassLogList.size();i++)
				{
					ClassLog mClassLog = mClassLogList.get(i);
//					log.updateDebug(mClassLog);
				}
				
				try {
					
					int interval = dao_time.getUpdateDataInterval();
					sleep(1000*interval);
					
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}


				if(mDeviceList!=null){
					log.debug(classname,"mDeviceList size" + mDeviceList.size());
				}else
				{
					continue;
				}
				
				System.out.println("");
				DevListDao dao1 = new DevListDao();
				oldDeviceList= dao1.getDeviceList();
				
				for(int i=0;i<oldDeviceList.size();i++)
				{
					log.debug(classname, "----+++---"+ oldDeviceList.get(i).getSn() +"---------"  +oldDeviceList.get(i).getOnline());
					
				}
				

				
				
				for(int i=0;i<mDeviceList.size();i++)
				{
					Device localDevice = mDeviceList.get(i);
					boolean sn_found = false;
					for(int j=0;j<oldDeviceList.size();j++)
					{
						Device sqlDevice = oldDeviceList.get(j);
						
						
						if(sqlDevice.getSn().equals(localDevice.getSn()) )
						{
							sn_found = true;
							log.debug(classname, "---------"+ localDevice.getSn() +"---------  "+sqlDevice.getOnline()+ ":"+localDevice.getOnline());
							if(sqlDevice.getOnline() != localDevice.getOnline())
								dao.updateDeviceOnLine(localDevice.getSn(), localDevice.getOnline());
						}
						
					}
					
					if(sn_found==false)
					{
						dao.updateDeviceOnLine(localDevice.getSn(), localDevice.getOnline());
						
					}
					
					
				}
				
								
				
				for(int i=0;i<mDeviceList.size();i++)
				{
					Device mDevice = mDeviceList.get(i);
					mDevice.setOnline(0);
				}
				
				
			}
		}
	}

	public void channelActive(ChannelHandlerContext ctx) {
//        ChannelMap.addChannel(ctx.channel().id().asShortText(), ctx.channel());
		log.info(classname,ctx.channel().localAddress().toString() + " 通道已激活！");

		log.debug(classname,"SN = " + sn);
		connectCount++;
		log.debug(classname, "---------open connectCount=" + connectCount + "-------");
	}

	/*
	 * channelInactive
	 *
	 * channel 通道 Inactive 不活跃的
	 *
	 * 当客户端主动断开服务端的链接后，这个通道就是不活跃的。也就是说客户端与服务端的关闭了通信通道并且不可以传输数据
	 *
	 */
	public void channelInactive(ChannelHandlerContext ctx) {
		ChannelMap.removeChannel(ctx.channel().id().asShortText());
		log.info(classname,ctx.channel().localAddress().toString() + " 通道不活跃，已关闭！");

		log.debug(classname,"SN = " + sn);
		log.debug(classname, "---------close connectCount=" + connectCount + "-------");
		connectCount--;
	}

	/**
	 * 功能：读取客户端发送过来的信息
	 */
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) {
//        SocketServerContext.setChannelId(ctx.channel().id().asShortText());
		log.debug(classname,"服务器开始处理数据.");

//        byte[] bytes = (byte[])msg;
		IntelligentMattressProtocol con = (IntelligentMattressProtocol) msg;
		System.out.println("==================="+con.toString());
		String recHex = ByteUtils.bytesToHexString(con.toSerReqBytes(), 0, con.toSerReqBytes().length);
		log.debug(classname,"recHex=" + recHex);
		//log.info("SN = " + con.getSn());
		sn =  con.getSn();

		if(mthread==null)
		{
			mthread = new CheckOnlineThread();
			mthread.start();
		}
		
		log.debug(classname,"  "+ sn +"  \n");
		if(sn!=null)
		{

			
			
		
			


			log.debug(classname,"sn!=null   " + sn );

			if(isDeviceExist(sn)==false)
			{
				Device mDevice = new Device(sn, 1);
				mDeviceList.add(mDevice);
			}
			
			setDeviceOnline(sn);
		}
		
		log.debug(classname,"CommandCode = " + String.valueOf(con.getCommandCode()));

		RequestHandlerProxy.handle(con);
	}

	/**
	 * 功能：读取完毕客户端发送过来的数据之后的操作
	 */
	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) {
		log.debug(classname,"服务端接收数据完毕..");
		
		log.debug(classname,"SN = " + sn);
		// 第一种方法：写一个空的buf，并刷新写出区域。//TODO 这里并未关闭连接
		ctx.writeAndFlush(Unpooled.EMPTY_BUFFER);
		// ctx.flush();
		// ctx.flush(); //
		// 第二种方法：在client端关闭channel连接，这样的话，会触发两次channelReadComplete方法。
		// ctx.flush().close().sync(); // 第三种：改成这种写法也可以，但是这中写法，没有第一种方法的好。
	}

	/**
	 * 功能：服务端发生异常的操作
	 */
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
		System.out.println("sn:"+sn);
		System.out.println("异常信息："+cause.getMessage());
		ctx.close();

		log.debug(classname,"SN = " + sn);
		log.debug(classname, "异常信息：" + cause.getMessage());
	}
}
