package com.gx.service;

import java.util.List;

import com.gx.entity.PageBean;
import com.gx.entity.Product;

public interface ProductService {

	List<Product> findHot() throws Exception;

	List<Product> findNew() throws Exception;

	Product getById(String pid) throws Exception;

	PageBean<Product> findByPage(int pageNumber, int pageSize, String cid) throws Exception;

	//PageBean<Product> findByPageAdmin(int pageNumber, int pageSize) throws Exception;

	List<Product> findAll() throws Exception;

	void save(Product product) throws Exception;
}
