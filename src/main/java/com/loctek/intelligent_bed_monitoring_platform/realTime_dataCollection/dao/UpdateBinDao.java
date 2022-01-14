package com.loctek.intelligent_bed_monitoring_platform.realTime_dataCollection.dao;

import com.loctek.intelligent_bed_monitoring_platform.realTime_dataCollection.utils.DataSourceUtils;
import com.loctek.intelligent_bed_monitoring_platform.realTime_dataCollection.utils.log;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;

public class UpdateBinDao {

	public boolean deleteUpdateFlag(String sn)
	{
//		log.debug("isNeedUpdate");
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		
		boolean ret = false;
		String sql = "DELETE from need_update_dev  where sn=?";

	     Object[] params={sn};
		        
		       
		 

		try {
			runner.update(sql,sn);
			ret = true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block

			log.error("DevListDao DELETE error");
			e.printStackTrace();
		}

		try {
			DataSourceUtils.closeConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ret;
	}
	public String getUpdateVersion(String sn)
	{
//		log.debug("isNeedUpdate");
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		
		String version = null;
		String sql = "select version from need_update_dev  where sn=?";

	     Object[] params={sn};
		        
		       
		 

		try {
			version=(String) runner.query(sql,new ScalarHandler(),params);
//			log.debug("version = " + version);
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
		return version;
		
	}
	
	public String getUpdatePath(String sn)
	{
//		log.debug("getUpdatePath");
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		
		String path = null;
		
		String version = null;
		String sql = "select path from update_version  where version=?";
		
		
		version = getUpdateVersion(sn);
		if(version==null)
			return null;
	
		
		

	     Object[] params={version};
		        
		       
		 

		try {
			path=(String) runner.query(sql,new ScalarHandler(),params);
//			log.debug("version = " + version);
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
		return path;
		
	}
	
	
	
	
}
