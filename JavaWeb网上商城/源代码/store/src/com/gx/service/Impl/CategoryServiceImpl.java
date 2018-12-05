package com.gx.service.Impl;

import java.util.List;

import redis.clients.jedis.Jedis;

import com.gx.constant.Constant;
import com.gx.dao.CategoryDao;
import com.gx.dao.Impl.CategoryDaoImpl;
import com.gx.entity.Category;
import com.gx.service.CategoryService;
import com.gx.utils.BeanFactory;
import com.gx.utils.JedisUtils;
import com.gx.utils.JsonUtil;

public class CategoryServiceImpl implements CategoryService {
	//CategoryDao categoryDao = new CategoryDaoImpl();
	CategoryDao categoryDao = (CategoryDao) BeanFactory.getBean("CategoryDao");
	
	/**
	 * 后台查询所有分类
	 */
	public List<Category> findList() throws Exception {
		// TODO Auto-generated method stub
		return categoryDao.findAll();
	}
	
	/**
	 * 查询所有分类
	 */
	public String findAll() throws Exception {

		//1、调用dao，查询所有分类
		List<Category> list = findList();
		//2、将list转换成json字符串
		if(list != null && list.size() > 0){
			return JsonUtil.list2json(list);
		}
		return null;
	}

	/**
	 * 后台添加分类
	 */
	public void save(Category category) throws Exception {

		categoryDao.save(category);
		//更新redis
		/*Jedis j = null;
		try {
			j = JedisUtils.getJedis();
			j.del(Constant.STORE_CATEGORY_LIST);
		} finally {
			JedisUtils.closeJedis(j);
		}*/
	}

	/**
	 * 后台删除分类
	 */
	public void del(String cid) throws Exception {
		categoryDao.del(cid);
	}

	/**
	 * 根据ID查询分类信息
	 */
	public Category getUpdId(String cid) throws Exception {
		// TODO Auto-generated method stub
		return categoryDao.getUpdId(cid);
	}

	/**
	 * 修改分类
	 */
	public void upd(Category category) throws Exception {
		// TODO Auto-generated method stub
		categoryDao.upd(category);
	}

	/**
	 * 从redis中获取所有分类
	 */
	/*public String findAllFromRedis() throws Exception {
		//1、获取redis
		Jedis jedis = JedisUtils.getJedis();
		//2、从reds中获取数据
		String value = jedis.get(Constant.STORE_CATEGORY_LIST);
		//3、判断是否为空
		if(value == null){
			//3.1若为空，调用findAll（），将查询到的数据放到redis中
			value = findAll();
			jedis.set(Constant.STORE_CATEGORY_LIST, value);
			System.out.println("从MySQL中获取");
			return value;
		}
		//3.2若不为空，return
		System.out.println("从redis中获取");
		return value;
	}*/

}
