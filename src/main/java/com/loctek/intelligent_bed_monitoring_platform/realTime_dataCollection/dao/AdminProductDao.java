package com.loctek.intelligent_bed_monitoring_platform.realTime_dataCollection.dao;



import com.loctek.intelligent_bed_monitoring_platform.realTime_dataCollection.domain.Product;
import com.loctek.intelligent_bed_monitoring_platform.realTime_dataCollection.utils.DataSourceUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;


public class AdminProductDao {

	public List<Product> findAllProduct() throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from product";
		List<Product> productList = runner.query(sql, new BeanListHandler<Product>(Product.class));
		DataSourceUtils.closeConnection();
		return productList;
	}


	public void addProduct(Product product) throws SQLException {
		

		Product tmpProduct= findProductBySN(product.getSn());
		if(tmpProduct!=null)
		{
			return ;
		}
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "insert into product values(?,?,?,?,?,?)";
		runner.update(sql, product.getProductID(),product.getSn(),product.getType(),
					product.getCustomerID(),product.getCPUID(),product.getOwerID());
		DataSourceUtils.closeConnection();
		
	}

	public void delProductByPid(String pid) throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "delete from product where ProductID=?";
		runner.update(sql, pid);
		DataSourceUtils.closeConnection();
		
	}

	public Product findProductByPid(String pid) throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from product where ProductID=?";
		Product product = runner.query(sql, new BeanHandler<Product>(Product.class), pid);
		DataSourceUtils.closeConnection();
		return product;
	}
	
	public Product findProductBySN(String SN) throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from product where SN=?";
		Product product = runner.query(sql, new BeanHandler<Product>(Product.class), SN);
		DataSourceUtils.closeConnection();
		return product;
	}
	
	

	public void updateProduct(Product product) throws SQLException {
//		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
//		String sql = "update product set pname=?,market_price=?,shop_price=?,pimage=?,pdate=?,is_hot=?,pdesc=?,pflag=?,cid=? where pid=?";
//		runner.update(sql,product.getPname(),product.getMarket_price(),
//				product.getShop_price(),product.getPimage(),product.getPdate(),product.getIs_hot(),
//				product.getPdesc(),product.getPflag(),product.getCid(),product.getPid());
	}

}
