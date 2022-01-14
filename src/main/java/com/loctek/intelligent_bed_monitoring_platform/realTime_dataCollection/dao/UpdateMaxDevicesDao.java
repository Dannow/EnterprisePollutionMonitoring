package com.loctek.intelligent_bed_monitoring_platform.realTime_dataCollection.dao;


import com.loctek.intelligent_bed_monitoring_platform.realTime_dataCollection.utils.DataSourceUtils;
import com.loctek.intelligent_bed_monitoring_platform.realTime_dataCollection.utils.log;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;

public class UpdateMaxDevicesDao {
	public int getUpdateMaxDevices()
	{
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		
		Integer max = new Integer(5);
		String sql = "select max from update_max_devices";

		 

		try {
			max =(Integer) runner.query(sql,new ScalarHandler());
//			log.error("max = " + max);
		} catch (SQLException e) {
			// TODO Auto-generated catch block

			log.error("getUpdateVersion error");
			e.printStackTrace();
		}

		try {
			DataSourceUtils.closeConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return max;
		
	}
}
