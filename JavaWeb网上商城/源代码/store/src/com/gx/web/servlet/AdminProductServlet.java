package com.gx.web.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gx.entity.Category;
import com.gx.entity.Product;
import com.gx.service.CategoryService;
import com.gx.service.ProductService;
import com.gx.utils.BeanFactory;
import com.gx.web.servlet.base.BaseServlet;

/**
 * 后台商品管理模块
 */
public class AdminProductServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	//ProductService productService = new ProductServiceImpl();
	ProductService productService = (ProductService)BeanFactory.getBean("ProductService");

	/**
	 * 跳转到添加页面，同时查出来分类列表
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String addUI(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			CategoryService categoryService= (CategoryService) BeanFactory.getBean("CategoryService");
			List<Category> list = categoryService.findList();
			request.setAttribute("list", list);
		} catch (Exception e) {
		}
		return "/admin/product/add.jsp";
	}
	
	/**
	 * 分页显示商品
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String findByPage(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/*try {
			//1、获取pageNumber，cid，设置pagesize
			int pageNumber = 1;
			//只是try起来，如果出错，显示pageNumber = 1;
			try {
				pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
			} catch (NumberFormatException e) {
			}
			int pageSize = 6;
			//2、调用service  分页查询商品参数：2个，返回值 pageBean
			PageBean<Product> pagebean = productService.findByPageAdmin(pageNumber,pageSize);
			//3、将pageBean放入request中，请求转发/jsp/product_list.jsp
			request.setAttribute("pb", pagebean);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
		return "/admin/product/list.jsp";*/
		try {
			//1.调用service 查询以上架商品
			List<Product> list = productService.findAll();
			
			//2.将返回值放入request中,请求转发
			request.setAttribute("list", list);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
		return "/admin/product/list.jsp";
	}
}
