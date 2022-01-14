package com.loctek.intelligent_bed_monitoring_platform.realTime_dataCollection.dao;


import com.loctek.intelligent_bed_monitoring_platform.realTime_dataCollection.das.util.ByteUtils;
import com.loctek.intelligent_bed_monitoring_platform.realTime_dataCollection.domain.Device;
import com.loctek.intelligent_bed_monitoring_platform.realTime_dataCollection.utils.DataSourceUtils;
import com.loctek.intelligent_bed_monitoring_platform.realTime_dataCollection.utils.log;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.List;

public class DevListDao {

	public int getDeviceExist(String sn)
	{
//		log.debug("DevListDao getDeviceExist");
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		
		 int topicNum=0;
		String sql = "select count(*) from devicelist where sn=?";

	     Object[] params={sn};
		        
		       
		 

		try {
			topicNum=(int)(long) runner.query(sql,new ScalarHandler(),params);
//			log.debug("topicNum = " + topicNum);
		} catch (SQLException e) {
			// TODO Auto-generated catch block

			log.error("DevListDao INSERT error");
			e.printStackTrace();
		}

		try {
			DataSourceUtils.closeConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return topicNum;
		
	}
	
	public void insertNewDevice(String sn, int online)
	{

		
		if(sn==null || sn.length()<10 || sn.equals("") || !ByteUtils.isNumeric(sn))
		{

			log.error("insertNewDevice sn="+sn
					+ "failed!!");
		
			return ;
		}
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "INSERT into devicelist(sn, online) VALUES(?,?)";

		try {
			runner.update(sql,  sn, online);
		} catch (SQLException e) {
			// TODO Auto-generated catch block

			log.error("DevListDao INSERT error");
			e.printStackTrace();
		}
		
		try {
			DataSourceUtils.closeConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	public void updateDeviceOnLine(String sn, int online)
	{

//		log.debug("DevListDao updateDeviceOnLine");
		
		if(getDeviceExist(sn)<=0){
			insertNewDevice(sn, online);
			return ;
		}
		
		
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "UPDATE devicelist set ONLINE = ? WHERE sn=?";
		try {
			runner.update(sql,  online, sn);
//			log.info("sn:"+ sn + "   online:"+online);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			log.error("DevListDao UPDATE error");

			e1.printStackTrace();
		}
		try {
			DataSourceUtils.closeConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			DataSourceUtils.closeConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public List<Device> getDeviceList()
	{

//		log.debug("DevListDao getDeviceList");
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from devicelist";
		
		
		List<Device> mDeviceList = null;
		try {
			Object[] params={};
	        
			mDeviceList = runner.query(sql, new BeanListHandler<Device>(Device.class), params);

			
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			log.error("DevListDao getDeviceList error");
			
			e1.printStackTrace();
		}
		try {
			DataSourceUtils.closeConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			DataSourceUtils.closeConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mDeviceList;
	}
}
	
	
