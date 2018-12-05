package com.gx.service;

import java.util.List;

import com.gx.entity.Category;

public interface CategoryService {

	String findAll() throws Exception;

	List<Category> findList() throws Exception;

	void save(Category category) throws Exception;

	void del(String cid) throws Exception;

	Category getUpdId(String cid) throws Exception;

	void upd(Category category) throws Exception;

	//String findAllFromRedis() throws Exception;

}
