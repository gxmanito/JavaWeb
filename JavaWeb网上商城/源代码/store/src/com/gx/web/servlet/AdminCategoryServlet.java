package com.gx.web.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gx.entity.Category;
import com.gx.service.CategoryService;
import com.gx.utils.BeanFactory;
import com.gx.utils.UUIDUtils;
import com.gx.web.servlet.base.BaseServlet;

/**
 * 后台分类管理
 */
public class AdminCategoryServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	//CategoryDao categoryDao = new CategoryDaoImpl();
	CategoryService categoryService= (CategoryService) BeanFactory.getBean("CategoryService");

	/**
	 * 修改分类信息
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String upd(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String cid = request.getParameter("cid");
			String cname = request.getParameter("cname");
			Category category = categoryService.getUpdId(cid);
			category.setCname(cname);
			categoryService.upd(category);
			response.sendRedirect(request.getContextPath()+"/adminCategory?method=findAll");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException();
		}
		return null;
	}
	
	/**
	 * 根据ID查询分类信息
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String getUpdId(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String cid = request.getParameter("cid");
			Category category  = categoryService.getUpdId(cid);
			request.setAttribute("category", category);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
		return "/admin/category/edit.jsp";
	}
	
	/**
	 * 删除分类
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String del(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		try {
			String cid = request.getParameter("cid");
			categoryService.del(cid);
			findAll(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
		return "/admin/category/list.jsp";
	}
	
	/**
	 *添加分类 
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String save(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		try {
			String cname = request.getParameter("cname");
			Category category = new Category();
			category.setCid(UUIDUtils.getId());
			category.setCname(cname);
			categoryService.save(category);
			response.sendRedirect(request.getContextPath()+"/adminCategory?method=findAll");
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
		return null;
	}
		
	/**
	 * 跳转到添加分类页面
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String addUI(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		return "/admin/category/add.jsp";
	}
	
	/**
	 * 显示所有分类
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String findAll(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			List<Category> list = categoryService.findList();
			request.setAttribute("list", list);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
		return "/admin/category/list.jsp";
	}
}
