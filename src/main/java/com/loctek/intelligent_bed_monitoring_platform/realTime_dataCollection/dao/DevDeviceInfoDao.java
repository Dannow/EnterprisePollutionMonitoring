/**
 * 
 */
package com.loctek.intelligent_bed_monitoring_platform.realTime_dataCollection.dao;

import com.loctek.intelligent_bed_monitoring_platform.realTime_dataCollection.das.socket.request.DevDeviceInfoRequest;
import com.loctek.intelligent_bed_monitoring_platform.realTime_dataCollection.utils.DataSourceUtils;
import com.loctek.intelligent_bed_monitoring_platform.realTime_dataCollection.utils.log;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;

/**
 * 设备信息表的Dao
 * @author Bing
 *
 */
public class DevDeviceInfoDao {

	/**
	 * 判断该设备是否已经在设备信息表中存在
	 * @param sn ：设备号
	 * @return
	 */
	public int getDeviceInfoExist(String sn)
	{
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		
		int topicNum=0;
		String sql = "select count(*) from dev_static_info where sn=?";
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


	/**
	 * 更新设备信息表在dev_dynamic_info中插入数据，如果设备信息表中没有该设备才进行添加
	 * @param request
	 * @throws SQLException
	 */
	public void update(DevDeviceInfoRequest request) throws SQLException {
		
		if(0 == getDeviceInfoExist(request.getSn()))
		{
			insert(request);
		}
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());

//		String sql = "UPDATE devicelist set ONLINE = ? WHERE sn=?";
		
		//插入静态数据
		String sql = "UPDATE dev_static_info set "
				+ "`fw_version`=?," +
				" `alg_version`=?," +
				" `boot_version`=?," +
				
				" `upgrade_flag`=?," +
				" `socketa_using_ip`=?," +
				" `socketa_using_port`=?," +
				
				" `socketa_ip`=?," +
				" `socketa_port`=?," +
				
				" `mac`=?" +
				
				" where sn=?";
		
		
		runner.update(sql, request.getFwVersion(), request.getAlgVersion(), request.getBootVersion(),
				request.getUpgradeFlag(), request.getSocketAUsingIp(), request.getSocketAUsingPort(),
				request.getSocketAIp(), request.getSocketAPort(),
				request.getMac(), request.getSn());
		
		//动态数据
		sql = "insert into dev_dynamic_info(`sn`,`calibration`,`device_enable`,`raw_data_enable`,`body_sign_freq`)"
				+ "VALUES(?,?,?,?,?)";

		runner.update(sql, request.getSn(),request.getCalibration() & 0xff, request.getDeviceEnable(), request.getRawDataEnable(),request.getBodySignFreq());
		
		
		DataSourceUtils.closeConnection();
	}

	/**
	 * 添加设备信息，这个函数是在update中才会被调用
	 * @param request
	 * @throws SQLException
	 */
	public void insert(DevDeviceInfoRequest request) throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "insert into dev_static_info"
				+ "(`sn`, `fw_version`, `alg_version`, `boot_version`, "
				+ "`upgrade_flag`, `socketa_using_ip`, `socketa_using_port`, "
				+ "`socketa_ip`, `socketa_port`,"
				+ " `mac`) "
				+ "VALUES(?, ?, ?, ?,"
				+ " ?, ?, ?,"
				+ " ?, ?, "
				+ "?)";
		runner.update(sql, request.getSn(), request.getFwVersion(), request.getAlgVersion(), request.getBootVersion(),
				request.getUpgradeFlag(), request.getSocketAUsingIp(), request.getSocketAUsingPort(),
				request.getSocketAIp(), request.getSocketAPort(),
				request.getMac());
		DataSourceUtils.closeConnection();
	}

	/**
	 * 找到设备绑定的用户
	 * @param sn
	 * @return
	 */
	public int getDeviceUser(String sn) throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select userID from dev_static_info where sn = ?";
		Object[] params={sn};
		int userID = (int) runner.query(sql, new ScalarHandler(), params);
		DataSourceUtils.closeConnection();
		return userID;
	}
}
