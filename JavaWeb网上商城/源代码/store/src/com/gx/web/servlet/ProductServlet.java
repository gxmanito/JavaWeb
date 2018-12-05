package com.gx.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gx.entity.PageBean;
import com.gx.entity.Product;
import com.gx.service.ProductService;
import com.gx.service.Impl.ProductServiceImpl;
import com.gx.utils.BeanFactory;
import com.gx.web.servlet.base.BaseServlet;

/**
 * 商品模块
 */
public class ProductServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	//ProductService productService = new ProductServiceImpl();
	ProductService productService = (ProductService)BeanFactory.getBean("ProductService");
	
	/**
	 * 分类商品分页展示
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String findByPage(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			//1、获取pageNumber，cid，设置pagesize
			int pageNumber = 1;
			//只是try起来，如果出错，显示pageNumber = 1;
			try {
				pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
			} catch (NumberFormatException e) {
			}
			int pageSize = 12;
			String cid = request.getParameter("cid");
			//2、调用service  分页查询商品参数：3个，返回值 pageBean
			PageBean<Product> pagebean = productService.findByPage(pageNumber,pageSize,cid);
			//3、将pageBean放入request中，请求转发/jsp/product_list.jsp
			request.setAttribute("pb", pagebean);
		} catch (Exception e) {
			request.setAttribute("msg", "分页查询失败！");
			return "/jsp/msg.jsp";
		}
		return "/jsp/product_list.jsp";
	}
	
	
	/**
	 * 商品详情
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String getById(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			//1、获取pid
			String pid = request.getParameter("pid");
			//2、调用service获取单个商品:pid 返回值：product
			Product pro = productService.getById(pid);
			//3、将product存到request域中，请求转发/jsp/product_info.jsp	
			request.setAttribute("bean", pro);
		} catch (Exception e) {
			request.setAttribute("msg", "查询单个商品失败！");
			return "/jsp/product_info.jsp";
		}
		return "/jsp/product_info.jsp";
	}
}
