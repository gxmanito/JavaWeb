package com.gx.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gx.service.CategoryService;
import com.gx.service.Impl.CategoryServiceImpl;
import com.gx.utils.BeanFactory;
import com.gx.web.servlet.base.BaseServlet;

/**
 * 前台分类模块
 */
public class CategoryServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	//CategoryService categoryService= new CategoryServiceImpl();
	CategoryService categoryService= (CategoryService) BeanFactory.getBean("CategoryService");

	
	/**
	 * 查询所有分类
	 */
	public String findAll(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		try {
			//0、设置 响应的编码
			response.setContentType("text/html;charset=utf-8");
			//1、调用service，查询所有分类，返回json字符串
			String value = categoryService.findAll();
			
			//从redis中获取数据
			//String value = categoryService.findAllFromRedis();
			
			//2、将字符串写回浏览器
			response.getWriter().println(value);
		} catch (Exception e) {
		}
		return null;
	}
}
