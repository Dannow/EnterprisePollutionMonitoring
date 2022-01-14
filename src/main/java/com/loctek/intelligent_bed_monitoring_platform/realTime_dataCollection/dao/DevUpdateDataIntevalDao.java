package com.loctek.intelligent_bed_monitoring_platform.realTime_dataCollection.dao;

import com.loctek.intelligent_bed_monitoring_platform.realTime_dataCollection.utils.DataSourceUtils;
import com.loctek.intelligent_bed_monitoring_platform.realTime_dataCollection.utils.log;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;

public class DevUpdateDataIntevalDao {
	public int getUpdateDataInterval()
	{
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		
		Integer interval = new Integer(31);
		String sql = "select time from dev_update_data_interval";

		 

		try {
			interval=(Integer) runner.query(sql,new ScalarHandler());
//			log.error("interval = " + interval);
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
		return interval;
		
	}
}
