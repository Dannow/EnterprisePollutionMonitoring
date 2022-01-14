package com.loctek.intelligent_bed_monitoring_platform.realTime_dataCollection.dao;

import com.loctek.intelligent_bed_monitoring_platform.common_model.domain.realTime_dataCollection.entity.BodySignal;
import com.loctek.intelligent_bed_monitoring_platform.common_model.domain.realTime_dataCollection.response.BodySignalResult;
import com.loctek.intelligent_bed_monitoring_platform.realTime_dataCollection.das.socket.request.DevBodySignRequest;
import com.loctek.intelligent_bed_monitoring_platform.realTime_dataCollection.domain.DevLocation;
import com.loctek.intelligent_bed_monitoring_platform.realTime_dataCollection.utils.DataSourceUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayListHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

@Repository
public class DevBodyDataDao {

	public void addBodySign(DevBodySignRequest data) throws SQLException {
//		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
//		String sql = "insert into jia_ococci_dev_body_sign(sn, heart_rate, breath_rate, heart_rate_cal, motion) values(?,?,?,?,?)";
//		runner.update(sql, data.getSn(), data.getHeartRate(), data.getBreathRate(), data.getHeartRateCal(),
//				data.getMotion());
//		DataSourceUtils.closeConnection();
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "insert into SampleData(sn, HeartRate, BreathRate, heartRateCal, motion) values(?,?,?,?,?)";
		runner.update(sql, data.getSn(), data.getHeartRate(), data.getBreathRate(), data.getHeartRateCal(),
				data.getMotion());
		DataSourceUtils.closeConnection();
	}

//	public List<DevBodySignRequest> findAllProduct() throws SQLException {
//		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
//		String sql = "select * from SampleData";
//		List<DevBodySignRequest> dataList = runner.query(sql,
//				new BeanListHandler<DevBodySignRequest>(DevBodySignRequest.class));
//		DataSourceUtils.closeConnection();
//		return dataList;
//	}

//	public void addProduct(DevBodySignRequest data) throws SQLException {
//		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
//		String sql = "insert into SampleData(sn, HeartRate, BreathRate, heartRateCal, motion) values(?,?,?,?,?)";
//		runner.update(sql, data.getSn(), data.getHeartRate(), data.getBreathRate(), data.getHeartRateCal(),
//				data.getMotion());
//		DataSourceUtils.closeConnection();
//	}

	public void updateProduct(DevBodySignRequest data) throws SQLException {
//		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
//		String sql = "update product set pname=?,market_price=?,shop_price=?,pimage=?,pdate=?,is_hot=?,pdesc=?,pflag=?,cid=? where pid=?";
//		runner.update(sql,product.getPname(),product.getMarket_price(),
//				product.getShop_price(),product.getPimage(),product.getPdate(),product.getIs_hot(),
//				product.getPdesc(),product.getPflag(),product.getCid(),product.getPid());
	}

	/**
	 * 根据时间获取特征数据
	 * @param time
	 * @param sn
	 * @return
	 * @throws SQLException
	 */
	public List<BodySignal> getBodySignalByTime(String sn,Date time) throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "SELECT * FROM sampledata e WHERE e.sn = ? and e.time > ? GROUP BY Day(e.time),HOUR(e.time) ORDER BY Did DESC";
		Object[] params={sn,time};
		List<BodySignal> bodySignalList = runner.query(sql, new BeanListHandler<>(BodySignal.class), params);
		DataSourceUtils.closeConnection();
		return bodySignalList;
	}

	/**
	 * 根据数量获取体征数据
	 * @param sn
	 * @param number
	 * @return
	 * @throws SQLException
	 */
	public List<BodySignal> getBodySignalByNumber(String sn, int number) throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "SELECT * FROM sampledata WHERE sn = ? ORDER BY time DESC LIMIT ?";
		Object[] params={sn, number};
		List<BodySignal> bodySignalList = runner.query(sql, new BeanListHandler<>(BodySignal.class), params);
		DataSourceUtils.closeConnection();
		return bodySignalList;
	}
}
