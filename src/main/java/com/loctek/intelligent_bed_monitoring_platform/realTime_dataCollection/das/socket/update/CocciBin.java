package com.loctek.intelligent_bed_monitoring_platform.realTime_dataCollection.das.socket.update;


import com.loctek.intelligent_bed_monitoring_platform.realTime_dataCollection.das.socket.dll.CalProcessProxy;
import com.loctek.intelligent_bed_monitoring_platform.realTime_dataCollection.das.socket.handler.DataPackageConstants;
import com.loctek.intelligent_bed_monitoring_platform.realTime_dataCollection.das.util.ByteUtils;
import com.loctek.intelligent_bed_monitoring_platform.realTime_dataCollection.utils.log;

import java.io.*;

public class CocciBin {
	

	int version;
	int fwType;
	long img_size;
	File file = null;
	byte[] data = null;
	byte BBCcheck;
	
	long timeLastRes = 0;
	
	int packetId;
	
	int packet_size = DataPackageConstants.UPDATE_PACKET_SIZE;
	
	int errReSendCount = 0;
	
	public CocciBin(String path)
	{
		
		file = new File(path);
		
		version = 0;
		fwType = -1;
		
		if(!ByteUtils.FileExists(file))
		{
			log.error(path +"is not exist!!!");
			setOpenFileFailed(path);
			return ;
		}
		
		CalProcessProxy.setPath(path);
		version = CalProcessProxy.getVersion();
		fwType = CalProcessProxy.getType();
		BBCcheck = (byte) CalProcessProxy.getBBCcheck();
		
		
		img_size = file.length();
		log.error("img_size="+img_size);
		
		int len = (int) img_size;
		data = new byte[len];
		try {
			FileInputStream in1 =new FileInputStream(file);
			BufferedInputStream inB = new BufferedInputStream(in1);
			System.out.print("\n");
			try {
				int ret = inB.read(data,0,len);
				log.error("ret = "+ret);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			

			System.out.print("\n");
			try {
				inB.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block

			setOpenFileFailed(path);
			e.printStackTrace();
		}
		
		
	}
	
	boolean bytesIsEqual(byte[] a, byte []b, int len)
	{
		int i = 0;
		for(i=0;i<len;i++)
		{
			if(a[i]!=b[i])
				return false;
		}
		return true;
	}
	String paraImgVersion()
	{
		String mVersion = null;
		
		
		
		
		
		return mVersion;
	}
	void setOpenFileFailed(String path)
	{
		log.error(path +" setOpenFileFailed!!");
		img_size = 0;
		version = 0;
		file = null;
		data = null;
	}
	public int getVersion()
	{
		return version;
	}
	public long getImg_size() {
		return img_size;
	}

	public int getFwType() {
		return fwType;
	}
	
	public byte getBBCcheck()
	{
		return BBCcheck;
	}

	public int getPacketId() {
		return packetId;
	}

	public void setPacketId(int packetId) {
		this.packetId = packetId;
	}
	
	public byte[]getData()
	{
		return data;
	}

	public long getTimeLastRes() {
		return timeLastRes;
	}

	public void setTimeLastRes(long timeLastRes) {
		this.timeLastRes = timeLastRes;
	}

	public int getPacket_size() {
		return packet_size;
	}

	public void setPacket_size(int packet_size) {
		this.packet_size = packet_size;
	}

	public int getErrReSendCount() {
		return errReSendCount;
	}

	public void setErrReSendCount(int errReSendCount) {
		this.errReSendCount = errReSendCount;
	}
	
	
	public void ErrCountIncrement()
	{
		errReSendCount++;
	}
	
	
}
