package com.loctek.intelligent_bed_monitoring_platform.realTime_dataCollection.dao;


import com.loctek.intelligent_bed_monitoring_platform.realTime_dataCollection.utils.ClassLog;
import com.loctek.intelligent_bed_monitoring_platform.realTime_dataCollection.utils.DataSourceUtils;
import com.loctek.intelligent_bed_monitoring_platform.realTime_dataCollection.utils.log;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

public class ClassLogDao {

	
	public List<ClassLog> getClassLog()
	{

//		log.debug("DevListDao getDeviceList");
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select classname,  value from server_log_debug";
		
		
		List<ClassLog> mClassLog = null;
		try {
			Object[] params={};
	        
			mClassLog = runner.query(sql, new BeanListHandler<ClassLog>(ClassLog.class), params);

			
			
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
		return mClassLog;
	}
	
	
}
