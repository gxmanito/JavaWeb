package com.gx.dao;

import java.util.List;

import com.gx.entity.PageBean;
import com.gx.entity.Product;

public interface ProductDao {

	List<Product> findHot() throws Exception;

	List<Product> findNew() throws Exception;

	Product getById(String pid) throws Exception;

	List<Product> findByPage(PageBean<Product> pageBean, String cid) throws Exception;

	int getTotalRecord(String cid) throws Exception;

	//List<Product> findByPageAdmin(PageBean<Product> pageBean) throws Exception;

	//int getTotalRecordAdmin() throws Exception;

	List<Product> findAll() throws Exception;

	void save(Product product) throws Exception;

}
