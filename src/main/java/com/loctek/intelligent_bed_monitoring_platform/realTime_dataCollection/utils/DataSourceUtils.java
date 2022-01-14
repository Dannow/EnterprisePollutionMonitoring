package com.loctek.intelligent_bed_monitoring_platform.realTime_dataCollection.utils;

import com.alibaba.druid.pool.DruidDataSource;
import com.loctek.intelligent_bed_monitoring_platform.realTime_dataCollection.das.util.SpringContextUtil;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@Component
public class DataSourceUtils {

	private static DruidDataSource dataSource = SpringContextUtil.getBean("dataSource");

	private static ThreadLocal<Connection> tl = new ThreadLocal<Connection>();

	// 直接可以获取�?个连接池
	public static DataSource getDataSource() {
		return dataSource;
	}
	
	public static Connection getConnection() throws SQLException{
		return dataSource.getConnection();
	}

	// 获取连接对象
	public static Connection getCurrentConnection() throws SQLException {
		Connection con = tl.get();
		if (con == null) {
			con = dataSource.getConnection();
			tl.set(con);
		}
		return con;
	}

//	// 获取连接对象
//	public static Connection getContainer() throws SQLException{
//		Connection conn = tl.get();
//		if(null == conn) {
//			// 获取数据库连接（共用框架内alibaba的druid）
//			DruidDataSource dataSource = SpringContextHolder.getBean("dataSource");
//			conn = dataSource.getConnection();
//			container.set(conn);
//		}
//		return conn;
//	}

	// �?启事�?
	public static void startTransaction() throws SQLException {
		Connection con = getCurrentConnection();
		if (con != null) {
			con.setAutoCommit(false);
		}
	}

	// 事务回滚
	public static void rollback() throws SQLException {
		Connection con = getCurrentConnection();
		if (con != null) {
			con.rollback();
		}
	}

	// 提交并且 关闭资源及从ThreadLocall中释�?
	public static void commitAndRelease() throws SQLException {
		Connection con = getCurrentConnection();
		if (con != null) {
			con.commit(); // 事务提交
			con.close();// 关闭资源
			tl.remove();// 从线程绑定中移除
		}
	}

	// 关闭资源方法
	public static void closeConnection() throws SQLException {
		Connection con = getCurrentConnection();
		if (con != null) {
			con.close();
		}
	}

	public static void closeStatement(Statement st) throws SQLException {
		if (st != null) {
			st.close();
		}
	}

	public static void closeResultSet(ResultSet rs) throws SQLException {
		if (rs != null) {
			rs.close();
		}
	}

}
