package com.loctek.intelligent_bed_monitoring_platform.realTime_dataCollection.das.socket.server;



import com.loctek.intelligent_bed_monitoring_platform.realTime_dataCollection.dao.UpdateBinDao;
import com.loctek.intelligent_bed_monitoring_platform.realTime_dataCollection.das.socket.channel.ChannelMap;
import com.loctek.intelligent_bed_monitoring_platform.realTime_dataCollection.das.socket.request.ServerRequest;
import com.loctek.intelligent_bed_monitoring_platform.realTime_dataCollection.das.socket.update.CocciBin;
import com.loctek.intelligent_bed_monitoring_platform.realTime_dataCollection.das.util.ByteUtils;
import com.loctek.intelligent_bed_monitoring_platform.realTime_dataCollection.utils.log;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;


import java.util.ArrayList;
import java.util.List;


public class PackageDecoder extends ByteToMessageDecoder
{

    /**
     * <pre>
     *  
     * 协议开始的标准head_data，int类型，占据4个字节. 
     * 表示数据的长度contentLength，int类型，占据4个字节.
     * </pre>
     */
	

	private static String classname = PackageDecoder.class.getSimpleName();
	
	
    public final int BASE_LENGTH = 15;
    private int old_len = 0;
    
    public static int UpdateMaxDevices = 5;
    
    
    public static void  setUpdateMaxDevices(int value)
    {
    	UpdateMaxDevices = value;
    }
    
    
    
    static List<DeviceMagager> deviceList = new ArrayList<>();
    
    
    public DeviceMagager updateProcess(String sn)
    {
    	DeviceMagager device = null;
    	for(int i=0;i<deviceList.size();i++)
    	{
    		DeviceMagager tempDevice = deviceList.get(i);
    		if(sn.equals(tempDevice.getSn()))
    		{
    			device = tempDevice;
    		}
    		
    	}
    	if(device==null)
    	{
    		device = new DeviceMagager(sn);
    		deviceList.add(device);
    	}
    	checkDeviceNeedUpdate(device);
    	return device;
    }
    
    
    static int UpdateDeviceCount = 0;
    
    static void UpdateDeviceCount_Reduce()
    {
    	if(UpdateDeviceCount>0)
    		UpdateDeviceCount--;
    }
    void checkDeviceNeedUpdate(DeviceMagager device)
    {
    	if(device.isNeedUpdate())
    	{
//			log.debug(classname,"isNeedUpdate");
    		return ;
    	}
    	
    	
    	UpdateBinDao uDao = new UpdateBinDao();
    	String path = uDao.getUpdatePath(device.getSn());
    	if(path!=null)
    	{
    		CocciBin bin = new CocciBin(path);
    		if(bin.getVersion()!=0)
    		{
    			log.debug(classname,"setBin");
    			device.setBin(bin);
    			if(UpdateDeviceCount>=UpdateMaxDevices)
    	    	{
    	    		log.debug(classname,"UpdateDeviceCount="+UpdateDeviceCount);
    	    		log.debug(classname,"UpdateMaxDevices="+UpdateMaxDevices);
    	    		return ;
    	    	}
    			
    			device.setNeedUpdate(true);
    			UpdateDeviceCount++;
    			
    		}
    		
    	}
          
    }

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf buffer, List<Object> out)
        throws Exception
    {
    	//log.debug(
//    	log.debug(classname,"---------------PackageDecoder-------------------");
    	log.debug(classname,"----------------------------------");
    	log.debug(classname,"----------------------------------");
    	log.debug(classname,"----------------------------------");
    	log.debug(classname,"------------hahdhddh---------------------");
    	int len = buffer.readableBytes();
    	log.debug(classname,"Len: "+ len);
//    	Byte[]temp = buffer.readby

    
    	
    	
        // 可读长度必须大于基本长度
        while (buffer.readableBytes() >= BASE_LENGTH)
        {
            // 防止socket字节流攻击
            // 防止，客户端传来的数据过大
            // 因为，太大的数据，是不合理的
            if (buffer.readableBytes() > 2048)
            {
                buffer.skipBytes(buffer.readableBytes());
            }

            // 记录包头开始的index
            int beginReader;

            while (true)
            {
                // 获取包头开始的index
                beginReader = buffer.readerIndex();
                // 标记包头开始的index
                buffer.markReaderIndex();
                // 读到了协议的开始标志，结束while循环
                byte byte1 = buffer.readByte();
                byte byte2 = buffer.readByte();
                int header1 = (((Byte)byte1).intValue() & 0xFF);
                int header2 = (((Byte)byte2).intValue() & 0xFF);
                if (header1 == IntelligentMattressProtocol.FIRST_HEAD_DATA
                    &&  header2 == IntelligentMattressProtocol.SECOND_HEAD_DATA)
                {
                    break;
                }

                // 未读到包头，略过一个字节
                // 每次略过，一个字节，去读取，包头信息的开始标记
                buffer.resetReaderIndex();
                buffer.readByte();

                // 当略过，一个字节之后，
                // 数据包的长度，又变得不满足
                // 此时，应该结束。等待后面的数据到达
                if (buffer.readableBytes() < BASE_LENGTH)
                {
                    return;
                }
            }

            // sn
            byte[] snBytes = new byte[10];
            buffer.readBytes(snBytes);
            String sn = new String(snBytes);
            

            DeviceMagager device = updateProcess(sn);
            
            
            ChannelMap.addSn(sn, ctx.channel(), device);

            byte lengthHByte = buffer.readByte();
            byte lengthLByte = buffer.readByte();
            byte[] lengthBytes = new byte[] {lengthHByte, lengthLByte};
            String lengthHex = ByteUtils.bytesToHexString(lengthBytes, 0, 2);
            Integer length = Integer.parseInt(lengthHex, 16);

            // 判断请求数据包数据是否到齐
            if (buffer.readableBytes() < length)
            {
                // 还原读指针
                buffer.readerIndex(beginReader);
                return;
            }

            byte commandCode = buffer.readByte();
            
            log.debug(classname,"commandCode="+commandCode);
            
            // 读取data数据
            byte[] data = new byte[length - 1];
            buffer.readBytes(data);

            ServerRequest request = new ServerRequest();
            
            request.setSn(sn);
            request.setDataHeader(commandCode);
            request.setContent(data);
            
            IntelligentMattressProtocol protocol = new IntelligentMattressProtocol(request);
            out.add(protocol);
        }
    }

}
