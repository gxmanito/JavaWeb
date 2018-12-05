package com.gx.dao.Impl;

import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.gx.constant.Constant;
import com.gx.dao.ProductDao;
import com.gx.entity.PageBean;
import com.gx.entity.Product;
import com.gx.utils.DataSourceUtils;

public class ProductDaoImpl implements ProductDao {
	QueryRunner queryRunner = new QueryRunner(DataSourceUtils.getDataSource());

	/**
	 * 查询热门商品
	 */
	public List<Product> findHot() throws Exception {
		// TODO Auto-generated method stub
		String sql = "select * from product where is_hot=? and pflag=? order by pdate desc limit 9";
		return queryRunner.query(sql, new BeanListHandler<Product>(Product.class),Constant.PRODUCT_IS_HOT,Constant.PRODUCT_IS_UP);
	}

	/**
	 * 查询最新商品
	 */
	public List<Product> findNew() throws Exception {
		String sql = "select * from product where pflag=? order by pdate desc limit 9";
		return queryRunner.query(sql, new BeanListHandler<Product>(Product.class),Constant.PRODUCT_IS_UP);
	}

	/**
	 * 查询单个商品
	 */
	public Product getById(String pid) throws Exception {
		String sql = "select * from product where pid=? limit 1";
		return queryRunner.query(sql, new BeanHandler<Product>(Product.class),pid);
	}
	
	/**
	 * 查询当前页数据
	 */
	public List<Product> findByPage(PageBean<Product> pageBean, String cid)
			throws Exception {

		String sql = "select * from product where cid=? and pflag=? order by pdate desc limit ?,?";
		return queryRunner.query(sql, new BeanListHandler<Product>(Product.class),cid,Constant.PRODUCT_IS_UP,pageBean.getStartIndex(),pageBean.getPageSize());
	}

	/**
	 * 查询总记录数
	 */
	public int getTotalRecord(String cid) throws Exception {

		String sql = "select count(*) from product where cid=? and pflag=? ";
		int count = ((Long) queryRunner.query(sql, new ScalarHandler(),cid,Constant.PRODUCT_IS_UP)).intValue();
		return count;
	}

	public List<Product> findAll() throws Exception {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from product where pflag = ? order by pdate desc";
		return qr.query(sql, new BeanListHandler<Product>(Product.class), Constant.PRODUCT_IS_UP);
	}

	/**
	 * 保存商品
	 */
	public void save(Product product) throws Exception {

		String sql = "insert into product values(?,?,?,?,?,?,?,?,?,?)";
		queryRunner.update(sql, product.getPid(),product.getPname(),product.getMarket_price()
				,product.getShop_price(),product.getPimage(),product.getPdate(),product.getIs_hot(),
				product.getPdesc(),product.getPflag(),product.getCategory().getCid());

	}

	/**
	 * 后台查询分页商品
	 */
	/*public List<Product> findByPageAdmin(PageBean<Product> pageBean)
			throws Exception {
		String sql = "select * from product where pflag=? order by pdate desc limit ?,?";
		return queryRunner.query(sql, new BeanListHandler<Product>(Product.class),Constant.PRODUCT_IS_UP,pageBean.getStartIndex(),pageBean.getPageSize());
	}

	public int getTotalRecordAdmin() throws Exception {
		String sql = "select count(*) from product where  pflag=? ";
		int count = ((Long) queryRunner.query(sql, new ScalarHandler(),Constant.PRODUCT_IS_UP)).intValue();
		return count;
	}*/

}
