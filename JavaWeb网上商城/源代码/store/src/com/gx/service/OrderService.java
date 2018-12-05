package com.gx.service;

import java.util.List;

import com.gx.entity.Order;
import com.gx.entity.PageBean;

public interface OrderService {

	void save(Order order) throws Exception;

	PageBean<Order> findMyOrderMyPage(int pageNumber, int pageSize, String uid) throws Exception;

	Order getById(String oid) throws Exception;

	void update(Order order) throws Exception;

	List<Order> findAllByState(String state) throws Exception;

//	PageBean<Order> findAllByState(int pageNumber, int pageSize, String state) throws Exception;

}
