package com.gx.web.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;

import com.gx.constant.Constant;
import com.gx.entity.Order;
import com.gx.entity.OrderItem;
import com.gx.entity.PageBean;
import com.gx.entity.User;
import com.gx.service.OrderService;
import com.gx.utils.BeanFactory;
import com.gx.utils.JsonUtil;
import com.gx.web.servlet.base.BaseServlet;

/**
 * 后台订单
 */
public class AdminOrderServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	//OrderService orderService = new OrderServiceImpl();
	OrderService orderService = (OrderService)BeanFactory.getBean("OrderService");

	/**
	 *  修改订单状态
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String updateState(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		try {
			String oid = request.getParameter("oid");
			Order order = orderService.getById(oid);
			order.setState(Constant.ORDER_YIFAHUO);
			orderService.update(order);
			response.sendRedirect(request.getContextPath()+"/adminOrder?method=findAllByState&state=1");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	/**
	 * 展示订单详情
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String showDetail(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			//设置编码  防止乱码
			response.setContentType("tetx/html;charset=utf-8");
			String oid = request.getParameter("oid");
			Order order = orderService.getById(oid);
			//获取订单的订单项列表转成json，写回浏览器
			if(order != null){
				List<OrderItem> list = order.getItems();
				if(list != null && list.size() > 0){
					//response.getWriter().println(JsonUtil.list2json(list));
					
					JsonConfig configJson = JsonUtil.configJson(new String[]{"order","pdate","pdesc","itemid"});
					response.getWriter().println(JSONArray.fromObject(list, configJson));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
		
		return null;
	}
	
	/**
	 * 后台按照状态查询订单列表
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String findAllByState(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			//获取state
			String state =request.getParameter("state");
			//调用service  获取不同的列表
			List<Order> list = orderService.findAllByState(state);
			//将list放入request域中，请求转发
			request.setAttribute("list", list);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
		return "/admin/order/list.jsp";
	}
	
	/*public String findAllByState(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		try {
			//1、获取pageNumber  设置pageSize  获取userID
			int pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
			int pageSize = 3;
			//获取state
			String state =request.getParameter("state");
			//2、调用service，获取当前页所有数据 pageBean
			PageBean<Order> bean = orderService.findAllByState(pageNumber,pageSize,state);
			//3、将pageBean放入request域中，请求转发order/list.jsp
			request.setAttribute("pb", bean);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
		return "/admin/order/list.jsp";
	}*/
}
