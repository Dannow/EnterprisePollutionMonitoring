/**
 * 
 */
package com.loctek.intelligent_bed_monitoring_platform.realTime_dataCollection.dao;



import com.loctek.intelligent_bed_monitoring_platform.realTime_dataCollection.das.socket.server.IntelligentMattressProtocol;

import java.sql.SQLException;

/**
 * @author Bing
 *
 */
public class DevLogDao {

	public void addLog(IntelligentMattressProtocol protocol) throws SQLException {
//		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
//		String sql = "insert into jia_ococci_dev_log(sn,command_code,hex) values(?,?,?)";
//		String recHex = ByteUtils.bytesToHexString(protocol.toSerReqBytes(), 0, protocol.toSerReqBytes().length);
//		runner.update(sql, protocol.getSn(), protocol.getCommandCode(), recHex);
//		DataSourceUtils.closeConnection();
	}

}
