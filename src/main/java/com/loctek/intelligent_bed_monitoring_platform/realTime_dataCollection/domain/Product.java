package com.loctek.intelligent_bed_monitoring_platform.realTime_dataCollection.domain;

public class Product {

	/*`pid` varchar(32) NOT NULL,
	  `pname` varchar(50) DEFAULT NULL,
	  `market_price` double DEFAULT NULL,
	  `shop_price` double DEFAULT NULL,
	  `pimage` varchar(200) DEFAULT NULL,
	  `pdate` date DEFAULT NULL,
	  `is_hot` int(11) DEFAULT NULL,
	  `pdesc` varchar(255) DEFAULT NULL,
	  `pflag` int(11) DEFAULT NULL,
	  `cid` varchar(32) DEFAULT NULL*/
	
	private String ProductID;
	private String sn;
	public String getSn() {
		return sn;
	}
	public void setSn(String sn) {
		this.sn = sn;
	}
	private String type;
	private int CustomerID;
	private int CPUID;
	private int OwerID;
	public String getProductID() {
		return ProductID;
	}
	public void setProductID(String productID) {
		ProductID = productID;
	}
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getCustomerID() {
		return CustomerID;
	}
	public void setCustomerID(int customerID) {
		CustomerID = customerID;
	}
	public int getCPUID() {
		return CPUID;
	}
	public void setCPUID(int cPUID) {
		CPUID = cPUID;
	}
	public int getOwerID() {
		return OwerID;
	}
	public void setOwerID(int owerID) {
		OwerID = owerID;
	}
	

	
	
	
}
