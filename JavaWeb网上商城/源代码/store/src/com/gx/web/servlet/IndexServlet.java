package com.gx.web.servlet;

import com.gx.entity.Product;
import com.gx.service.ProductService;
import com.gx.service.Impl.ProductServiceImpl;
import com.gx.web.servlet.base.BaseServlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 首页模块
 */
public class IndexServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	ProductService productService = new ProductServiceImpl();

	public String index(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			//1、调用productService查询最新商品和热门商品
			List<Product> hotList = productService.findHot();
			List<Product> newList = productService.findNew();
			//2、将两个list都方法request域中
			request.setAttribute("hList", hotList);
			request.setAttribute("nList", newList);
		} catch (Exception e) {
		}
		return "/jsp/index.jsp";
	}
}
