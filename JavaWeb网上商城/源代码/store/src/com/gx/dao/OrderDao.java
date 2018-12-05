package com.gx.dao;

import java.util.List;

import com.gx.entity.Order;
import com.gx.entity.OrderItem;
import com.gx.entity.PageBean;

public interface OrderDao {

	void save(Order order) throws Exception;

	void saveItem(OrderItem oi) throws Exception;

	int getTotalRecord(String uid) throws Exception;

	List<Order> findMyOrderMyPage(PageBean<Order> pb, String uid) throws Exception;

	Order getById(String oid) throws Exception;

	void update(Order order) throws Exception;

	List<Order> findAllByState(String state) throws Exception;

//	int getTotalRecordadmin(String state) throws Exception;
//
//	List<Order> findAllByState(PageBean<Order> pb, String state) throws Exception;

}
