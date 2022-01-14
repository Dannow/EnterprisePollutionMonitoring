package com.loctek.intelligent_bed_monitoring_platform.realTime_dataCollection.das.socket.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseRequest
{
    
    private String sn;

    private int dataHeader;

	/**
	 * @return the dataHeader
	 */
	public int getDataHeader() {
		return dataHeader;
	}

	/**
	 * @param dataHeader the dataHeader to set
	 */
	public void setDataHeader(int dataHeader) {
		this.dataHeader = dataHeader;
	}

	/**
	 * @return the sn
	 */
	public String getSn() {
		return sn;
	}

	/**
	 * @param sn the sn to set
	 */
	public void setSn(String sn) {
		this.sn = sn;
	}
    
}
