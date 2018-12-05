package com.gx.service.Impl;

import java.util.List;

import com.gx.dao.OrderDao;
import com.gx.entity.Order;
import com.gx.entity.OrderItem;
import com.gx.entity.PageBean;
import com.gx.service.OrderService;
import com.gx.utils.BeanFactory;
import com.gx.utils.DataSourceUtils;

public class OrderServiceImpl implements OrderService {
	//OrderDao orderDao = new OrderDaoImpl();
	OrderDao orderDao = (OrderDao)BeanFactory.getBean("OrderDao");
	
	/**
	 * 保存订单
	 */
	public void save(Order order) throws Exception{

		try {
			//1、开启事务（编写service:开启事务，向orders保存一条，向orderitem中保存多条）
			DataSourceUtils.startTransaction();
			//2、向order表中插入一条
			orderDao.save(order);
			//3、向orderitem中插入n条
			for (OrderItem oi: order.getItems()) {
				orderDao.saveItem(oi);
			}
			//4、事务控制
			DataSourceUtils.commitAndClose();
		} catch (Exception e) {
			e.printStackTrace();
			DataSourceUtils.rollbackAndClose();//回滚
			throw e;
		}
	}

	/**
	 * 我的订单
	 */
	public PageBean<Order> findMyOrderMyPage(int pageNumber, int pageSize,
			String uid) throws Exception {

		//1、创建pageBean
		PageBean<Order> pb = new PageBean<Order>(pageNumber, pageSize);
		///2、查询总条数，设置总条数
		int totalRecord = orderDao.getTotalRecord(uid);
		pb.setTotalRecord(totalRecord);
		//3、查询当前页数据，设置当前页数据
		List<Order> data = orderDao.findMyOrderMyPage(pb,uid);
		pb.setData(data);
		return pb;
	}

	/**
	 * 订单详情
	 */
	public Order getById(String oid) throws Exception {
		// TODO Auto-generated method stub
		return orderDao.getById(oid);
	}

	/**
	 * 修改订单
	 */
	public void update(Order order) throws Exception {
		// TODO Auto-generated method stub
		orderDao.update(order);
	}

	/**
	 * 后台查询订单名称
	 */
	public List<Order> findAllByState(String state) throws Exception {
		
		return orderDao.findAllByState(state);
	}

	/*public PageBean<Order> findAllByState(int pageNumber, int pageSize,
			String state) throws Exception {
		//1、创建pageBean
		PageBean<Order> pb = new PageBean<Order>(pageNumber, pageSize);
		///2、查询总条数，设置总条数
		int totalRecord = orderDao.getTotalRecordadmin(state);
		pb.setTotalRecord(totalRecord);
		//3、查询当前页数据，设置当前页数据
		List<Order> data = orderDao.findAllByState(pb,state);
		pb.setData(data);
		return pb;
	}*/

}
