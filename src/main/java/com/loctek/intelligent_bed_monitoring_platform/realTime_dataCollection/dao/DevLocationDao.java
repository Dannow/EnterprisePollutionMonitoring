package com.loctek.intelligent_bed_monitoring_platform.realTime_dataCollection.dao;


import com.loctek.intelligent_bed_monitoring_platform.realTime_dataCollection.das.socket.request.DevGsmGpsInfoRequest;
import com.loctek.intelligent_bed_monitoring_platform.realTime_dataCollection.domain.DevLocation;
import com.loctek.intelligent_bed_monitoring_platform.realTime_dataCollection.utils.DataSourceUtils;
import com.loctek.intelligent_bed_monitoring_platform.realTime_dataCollection.utils.log;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;

@Repository
public class DevLocationDao {
	public void insertNewLocation(DevGsmGpsInfoRequest request)
	{
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "INSERT into dev_location(sn, latitude, longitude) VALUES(?,?,?)";

		try {
			runner.update(sql, request.getSn(), request.getmLocation().getLatitude(), request.getmLocation().getLongitude());
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

	/**
	 * 获取设备的位置信息
	 * @param sn
	 * @return
	 */
	public DevLocation getLocation(String sn) throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "SELECT latitude,longitude FROM `dev_location` WHERE sn = ? ORDER BY time DESC LIMIT 1";
		Object[] params={sn};
		DevLocation devLocation = runner.query(sql, new BeanHandler<>(DevLocation.class),params);
		DataSourceUtils.closeConnection();
		return devLocation;
	}
}
