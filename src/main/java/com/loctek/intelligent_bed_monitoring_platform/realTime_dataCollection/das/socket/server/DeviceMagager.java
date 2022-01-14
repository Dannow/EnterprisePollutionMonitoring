package com.loctek.intelligent_bed_monitoring_platform.realTime_dataCollection.das.socket.server;


import com.loctek.intelligent_bed_monitoring_platform.realTime_dataCollection.dao.UpdateBinDao;
import com.loctek.intelligent_bed_monitoring_platform.realTime_dataCollection.das.socket.update.CocciBin;

public class DeviceMagager {
	String sn;
	boolean needUpdate;
	boolean updating;
	CocciBin bin;
	
	public DeviceMagager(String value)
	{
		sn = value;
		needUpdate = false;
		updating = false;
		bin = null;
	}
	
	public String getSn() {
		return sn;
	}
	public void setSn(String sn) {
		this.sn = sn;
	}
	public boolean isNeedUpdate() {
		return needUpdate;
	}
	public void setNeedUpdate(boolean needUpdate) {
		this.needUpdate = needUpdate;
		
	}
	public boolean isUpdating() {
		return updating;
	}
	public void setUpdating(boolean updating) {
		this.updating = updating;
	}
	
	public void finishUpdate()
	{
		updating = false;
		needUpdate = false;
		PackageDecoder.UpdateDeviceCount_Reduce();
		
		//修改数据库
		UpdateBinDao dao = new UpdateBinDao();
		dao.deleteUpdateFlag(sn);
		//删除chanel
	}

	public CocciBin getBin() {
		return bin;
	}

	public void setBin(CocciBin bin) {
		this.bin = bin;
	}
	
	
	
}
