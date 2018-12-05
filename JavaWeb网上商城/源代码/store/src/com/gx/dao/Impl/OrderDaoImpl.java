package com.gx.dao.Impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.gx.dao.OrderDao;
import com.gx.entity.Order;
import com.gx.entity.OrderItem;
import com.gx.entity.PageBean;
import com.gx.entity.Product;
import com.gx.utils.DataSourceUtils;

public class OrderDaoImpl implements OrderDao {
	

	/**
	 * 保存订单
	 */
	public void save(Order order) throws Exception {
		QueryRunner queryRunner = new QueryRunner();
		String sql = "insert into orders values(?,?,?,?,?,?,?,?)";
		queryRunner.update(DataSourceUtils.getConnection(),sql,order.getOid(),order.getOrdertime(),order.getTotal(),
				order.getState(),order.getAddress(),order.getName(),order.getTelephone(),order.getUser().getUid());
	}

	/**
	 * 保存订单项
	 */
	public void saveItem(OrderItem oi) throws Exception {
		QueryRunner queryRunner = new QueryRunner();
		String sql = "insert into orderitem values(?,?,?,?,?)";
		queryRunner.update(DataSourceUtils.getConnection(),sql, oi.getItemid(),oi.getCount(),oi.getSubtotal(),oi.getProduct().getPid(),oi.getOrder().getOid());
	}

	/**
	 * 获取我的订单总记录数
	 */
	public int getTotalRecord(String uid) throws Exception {
		QueryRunner queryRunner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select count(*) from orders where uid = ?";
		int count =  ((Long)queryRunner.query(sql, new ScalarHandler(),uid)).intValue();
		return count;
	}

	/**
	 * 获取我的订单当前数据
	 */
	public List<Order> findMyOrderMyPage(PageBean<Order> pb, String uid)
			throws Exception {
		QueryRunner queryRunner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from orders where uid = ? order by ordertime desc limit ?,?";
		List<Order> list = queryRunner.query(sql, new BeanListHandler<Order>(Order.class),uid,pb.getStartIndex(),pb.getPageSize());
		//遍历订单的集合，获取每一个订单，查询每一个订单项
		for (Order order : list) {
			sql = "select * from orderitem oi,product p where oi.pid = p.pid and oi.oid=? ";
			List<Map<String, Object>> maplist = queryRunner.query(sql,new MapListHandler(),order.getOid());
			//遍历maplist，获取每一个订单项详情，封装orderitem，将其加入每个订单的订单项列表中
			for (Map<String, Object> map : maplist) {
				//1、封装成orderitem，创建orderitem
				OrderItem oi = new OrderItem();
				//封装orderitem
				BeanUtils.populate(oi, map);
				//手动封装product
				Product p =new Product();
				BeanUtils.populate(p, map);
				oi.setProduct(p);
				//2、将orderitem放入订单项列表
				order.getItems().add(oi);
			}
		}
		return list;
	}

	/**
	 * 订单详情
	 */
	public Order getById(String oid) throws Exception {
		//1、查询订单基本信息
		QueryRunner queryRunner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from orders where oid = ?";
		Order order = queryRunner.query(sql, new BeanHandler<Order>(Order.class),oid);
		//2、查询订单项
		sql = "select * from orderitem oi,product p where oi.pid = p.pid and oi.oid=? ";
		//所有的订单详情
		List<Map<String, Object>> maplist = queryRunner.query(sql,new MapListHandler(),oid);
		//遍历获取每一个订单项详情，封装orderitem，加入当前订单的items中
		for (Map<String, Object> map : maplist) {
			//1、封装成orderitem，创建orderitem
			OrderItem oi = new OrderItem();
			//封装orderitem
			BeanUtils.populate(oi, map);
			//手动封装product
			Product p =new Product();
			BeanUtils.populate(p, map);
			oi.setProduct(p);
			//2、将orderitem放入订单项列表
			order.getItems().add(oi);
		}
		return order;
	}

	public void update(Order order) throws Exception {
		QueryRunner queryRunner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "update orders set state=?,address=?,name=?,telephone=? where oid=?";
		queryRunner.update(sql, order.getState(),order.getAddress(),order.getName(),order.getTelephone(),order.getOid());
	}

	/**
	 * 后台查询订单列表
	 */
	public List<Order> findAllByState(String state) throws Exception {
		QueryRunner queryRunner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from orders ";
		//判断state是否为空
		if(null == state || state.trim().length() == 0){
			sql +=" order by ordertime desc";
			return queryRunner.query(sql, new BeanListHandler<Order>(Order.class));
		}
		sql += " where state = ? order by ordertime desc";
		return queryRunner.query(sql, new BeanListHandler<Order>(Order.class),state);
	}

	/*public int getTotalRecordadmin(String state) throws Exception {
		
		QueryRunner queryRunner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select count(*) from orders where state = ?";
		int count =  ((Long)queryRunner.query(sql, new ScalarHandler(),state)).intValue();
		return count;
	}*/

	/*public List<Order> findAllByState(PageBean<Order> pb, String state)
			throws Exception {
		QueryRunner queryRunner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from orders ";
		//判断state是否为空
		if(null == state || state.trim().length() == 0){
			sql +=" order by ordertime desc limit ?,?";
			return queryRunner.query(sql, new BeanListHandler<Order>(Order.class),pb.getStartIndex(),pb.getPageSize());
		}
		sql = " where state = ? order by ordertime desc limit ?,?";
		return queryRunner.query(sql, new BeanListHandler<Order>(Order.class),state,pb.getStartIndex(),pb.getPageSize());
	}*/

}
