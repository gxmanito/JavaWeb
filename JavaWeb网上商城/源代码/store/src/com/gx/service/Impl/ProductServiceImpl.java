package com.gx.service.Impl;

import java.util.List;

import com.gx.dao.ProductDao;
import com.gx.dao.Impl.ProductDaoImpl;
import com.gx.entity.PageBean;
import com.gx.entity.Product;
import com.gx.service.ProductService;
import com.gx.utils.BeanFactory;

public class ProductServiceImpl implements ProductService {
	//ProductDao productDao = new ProductDaoImpl();
	ProductDao productDao = (ProductDao) BeanFactory.getBean("ProductDao");

	/**
	 * 查询热门商品
	 */
	public List<Product> findHot() throws Exception {
		// TODO Auto-generated method stub
		return productDao.findHot();
	}

	/**
	 * 查询最新商品
	 */
	public List<Product> findNew() throws Exception {
		// TODO Auto-generated method stub
		return productDao.findNew();
	}

	/**
	 * 单个商品详情
	 */
	public Product getById(String pid) throws Exception {
		// TODO Auto-generated method stub
		return productDao.getById(pid);
	}

	/**
	 * 分页展示商品
	 */
	public PageBean<Product> findByPage(int pageNumber, int pageSize, String cid)
			throws Exception {

		//1、创建pageBean
		PageBean<Product> pageBean = new PageBean<Product>(pageNumber, pageSize);
		//2、设置当前页数据
		List<Product> data = productDao.findByPage(pageBean,cid);
		pageBean.setData(data);
		//3、设置总记录数
		int totalRecord = productDao.getTotalRecord(cid);
		pageBean.setTotalRecord(totalRecord);
		return pageBean;
	}

	public List<Product> findAll() throws Exception {
		// TODO Auto-generated method stub
		return productDao.findAll();
	}

	/**
	 * 保存商品
	 */
	public void save(Product product) throws Exception {
		// TODO Auto-generated method stub
		productDao.save(product);
	}


	/**
	 * 后台分页展示商品
	 */
	/*public PageBean<Product> findByPageAdmin(int pageNumber, int pageSize)
			throws Exception {
		//1、创建pageBean
		PageBean<Product> pageBean = new PageBean<Product>(pageNumber, pageSize);
		//2、设置当前页数据
		List<Product> data = productDao.findByPageAdmin(pageBean);
		pageBean.setData(data);
		//3、设置总记录数
		int totalRecord = productDao.getTotalRecordAdmin();
		System.out.println(totalRecord);
		pageBean.setTotalRecord(totalRecord);
		return pageBean;
	}*/

}
