/**
 * 
 */
package com.loctek.intelligent_bed_monitoring_platform.realTime_dataCollection.dao;

import com.loctek.intelligent_bed_monitoring_platform.realTime_dataCollection.das.socket.request.DevSyncTimeReqRequest;
import com.loctek.intelligent_bed_monitoring_platform.realTime_dataCollection.utils.DataSourceUtils;
import org.apache.commons.dbutils.QueryRunner;

import java.sql.SQLException;

/**
 * @author Bing
 *
 */
public class DevSyncTimeReqDao {

	public void insert(DevSyncTimeReqRequest request) throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "insert into SyncTime(`sn`, `device_second`) VALUES (?, ?)";
		runner.update(sql, request.getSn(), request.getTime());
		DataSourceUtils.closeConnection();
	}

}
