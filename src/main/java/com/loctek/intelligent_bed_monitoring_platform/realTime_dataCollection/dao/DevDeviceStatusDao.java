/**
 * 
 */
package com.loctek.intelligent_bed_monitoring_platform.realTime_dataCollection.dao;

import com.loctek.intelligent_bed_monitoring_platform.realTime_dataCollection.das.socket.request.DevDeviceStatusRequest;
import com.loctek.intelligent_bed_monitoring_platform.realTime_dataCollection.utils.DataSourceUtils;
import org.apache.commons.dbutils.QueryRunner;

import java.sql.SQLException;

/**
 * @author Bing
 *
 */
public class DevDeviceStatusDao {

	public void insert(DevDeviceStatusRequest request) throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "insert into devStatus(`sn`, `device_status`, `connect_status`) VALUES (?, ?, ?)";
		runner.update(sql, request.getSn(), request.getDeviceStatus(), request.getConnectStatus());
		DataSourceUtils.closeConnection();
	}

}
