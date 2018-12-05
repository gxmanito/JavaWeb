package com.gx.web.servlet;

import java.io.IOException;
import java.util.Date;
import java.util.ResourceBundle;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gx.constant.Constant;
import com.gx.entity.Cart;
import com.gx.entity.CartItem;
import com.gx.entity.Order;
import com.gx.entity.OrderItem;
import com.gx.entity.PageBean;
import com.gx.entity.User;
import com.gx.service.OrderService;
import com.gx.utils.BeanFactory;
import com.gx.utils.PaymentUtil;
import com.gx.utils.UUIDUtils;
import com.gx.web.servlet.base.BaseServlet;

/**
 * 订单模块
 */
public class OrderServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	//OrderService orderService = new OrderServiceImpl();
	OrderService orderService = (OrderService)BeanFactory.getBean("OrderService");

	/**
	 * 获取订单详情
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String getById(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String oid = request.getParameter("oid");
			Order order = orderService.getById(oid);
			request.setAttribute("bean", order);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", "查询订单详情失败！");	
			return "/jsp/msg.jsp";
		}
		return "/jsp/order_info.jsp";
	}
	
	/**
	 * 我的订单（分页显示）
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String findMyOrderMyPage(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		try {
			//1、获取pageNumber  设置pageSize  获取userID
			int pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
			int pageSize = 3;
			User user = (User)request.getSession().getAttribute("user");
			if(user == null){
				request.setAttribute("msg", "请先登录！");
				return "/jsp/msg.jsp";
			}
			//2、调用service，获取当前页所有数据 pageBean
			PageBean<Order> bean = orderService.findMyOrderMyPage(pageNumber,pageSize,user.getUid());
			//3、将pageBean放入request域中，请求转发order_list.jsp
			request.setAttribute("pb", bean);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", "获取我的订单失败！");
			return "/jsp/msg.jsp";
		}
		return "/jsp/order_list.jsp";
	}
	
	/**
	 * 保存订单
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String save(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			//0 、获取user（从session中取）
			User user = (User)request.getSession().getAttribute("user");
			if(user == null){
				//未登录
				request.setAttribute("msg", "请先登录！");
				return "/jsp/msg.jsp";
				//response.sendRedirect("/jsp/login.jsp");
			}
			//1、获取购物车（从session中取出）
			Cart cart = (Cart)request.getSession().getAttribute("cart");
			//2、封装订单，set值
			Order order = new Order();
			order.setOid(UUIDUtils.getId());
			order.setOrdertime(new Date());
			order.setTotal(cart.getTotal());
			order.setState(Constant.ORDER_WEIFUKUAN);
			order.setUser(user);
			//3、设置items（订单项列表），遍历购物项列表
			for (CartItem ci: cart.getCartItems()) {
				//封装orderitem
				//创建orderitem
				OrderItem orderItem = new OrderItem();
				orderItem.setItemid(UUIDUtils.getId());
				orderItem.setCount(ci.getCount());
				orderItem.setSubtotal(ci.getSubtotal());
				orderItem.setProduct(ci.getProduct());
				orderItem.setOrder(order);
				//将orderitem加入到order的items中
				order.getItems().add(orderItem);
			}
			//4、调用service完成保存操作
			orderService.save(order);
			//请求转发到order_info.jsp
			request.setAttribute("bean", order);
			cart.clearCard();
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", "生成订单失败！");
			return "/jsp/msg.jsp";
		}
		return "/jsp/order_info.jsp";
	}
	
	/**
	 * 在线支付
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String pay(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//1、获取收获信息，oid，银行
		//2、调用service获取订单，修改收货人信息，更新订单
		//3、拼接给第三方的URL
		//4、返回

		//接受参数
		try {
			String address=request.getParameter("address");
			String name=request.getParameter("name");
			String telephone=request.getParameter("telephone");
			String oid=request.getParameter("oid");
			
			
			//通过id获取order
			Order order = orderService.getById(oid);
			
			order.setAddress(address);
			order.setName(name);
			order.setTelephone(telephone);
			
			//更新order
			orderService.update(order);

			// 组织发送支付公司需要哪些数据
			String pd_FrpId = request.getParameter("pd_FrpId");//银行通道
			String p0_Cmd = "Buy";//业务类型
			//jdk自带的ResourceBundle  可以自动使用src目录下merchantInfo（注意不带后缀名）
			String p1_MerId = ResourceBundle.getBundle("merchantInfo").getString("p1_MerId");//商户编号
			String p2_Order = oid;//订单ID
			String p3_Amt = "0.01";//支付金额
			String p4_Cur = "CNY";//人民币
			String p5_Pid = "";
			String p6_Pcat = "";
			String p7_Pdesc = "";
			// 支付成功回调地址 ---- 第三方支付公司会访问、用户访问
			// 第三方支付可以访问网址
			String p8_Url = ResourceBundle.getBundle("merchantInfo").getString("responseURL");
			String p9_SAF = "";
			String pa_MP = "";
			String pr_NeedResponse = "1";
			// 加密hmac 需要密钥
			String keyValue = ResourceBundle.getBundle("merchantInfo").getString("keyValue");
			String hmac = PaymentUtil.buildHmac(p0_Cmd, p1_MerId, p2_Order, p3_Amt,
					p4_Cur, p5_Pid, p6_Pcat, p7_Pdesc, p8_Url, p9_SAF, pa_MP,
					pd_FrpId, pr_NeedResponse, keyValue);
			
			//发送给第三方
			StringBuffer sb = new StringBuffer("https://www.yeepay.com/app-merchant-proxy/node?");
			sb.append("p0_Cmd=").append(p0_Cmd).append("&");
			sb.append("p1_MerId=").append(p1_MerId).append("&");
			sb.append("p2_Order=").append(p2_Order).append("&");
			sb.append("p3_Amt=").append(p3_Amt).append("&");
			sb.append("p4_Cur=").append(p4_Cur).append("&");
			sb.append("p5_Pid=").append(p5_Pid).append("&");
			sb.append("p6_Pcat=").append(p6_Pcat).append("&");
			sb.append("p7_Pdesc=").append(p7_Pdesc).append("&");
			sb.append("p8_Url=").append(p8_Url).append("&");
			sb.append("p9_SAF=").append(p9_SAF).append("&");
			sb.append("pa_MP=").append(pa_MP).append("&");
			sb.append("pd_FrpId=").append(pd_FrpId).append("&");
			sb.append("pr_NeedResponse=").append(pr_NeedResponse).append("&");
			sb.append("hmac=").append(hmac);
			
			response.sendRedirect(sb.toString());
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", "支付失败！");
			return "/jsp/msg.jsp";
		}
		return null;
	}

	/**
	 * 支付成功之后的回调
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String callback(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//1、获取第三方发送过来的数据
		//2、获取订单啊，修改订单状态
		//3更新订单
		try {
			String p1_MerId = request.getParameter("p1_MerId");
			String r0_Cmd = request.getParameter("r0_Cmd");
			String r1_Code = request.getParameter("r1_Code");
			String r2_TrxId = request.getParameter("r2_TrxId");
			String r3_Amt = request.getParameter("r3_Amt");
			String r4_Cur = request.getParameter("r4_Cur");
			String r5_Pid = request.getParameter("r5_Pid");
			String r6_Order = request.getParameter("r6_Order");
			String r7_Uid = request.getParameter("r7_Uid");
			String r8_MP = request.getParameter("r8_MP");
			String r9_BType = request.getParameter("r9_BType");
			String rb_BankId = request.getParameter("rb_BankId");
			String ro_BankOrderId = request.getParameter("ro_BankOrderId");
			String rp_PayDate = request.getParameter("rp_PayDate");
			String rq_CardNo = request.getParameter("rq_CardNo");
			String ru_Trxtime = request.getParameter("ru_Trxtime");
			// 身份校验 --- 判断是不是支付公司通知你
			String hmac = request.getParameter("hmac");
			String keyValue = ResourceBundle.getBundle("merchantInfo").getString(
					"keyValue");

			// 自己对上面数据进行加密 --- 比较支付公司发过来hamc
			boolean isValid = PaymentUtil.verifyCallback(hmac, p1_MerId, r0_Cmd,
					r1_Code, r2_TrxId, r3_Amt, r4_Cur, r5_Pid, r6_Order, r7_Uid,
					r8_MP, r9_BType, keyValue);
			if (isValid) {
				// 响应数据有效
				if (r9_BType.equals("1")) {
					// 浏览器重定向
					System.out.println("111");
					request.setAttribute("msg", "您的订单号为:"+r6_Order+",金额为:"+r3_Amt+"已经支付成功,等待发货~~");
					
				} else if (r9_BType.equals("2")) {
					// 服务器点对点 --- 支付公司通知你
					System.out.println("付款成功！222");
					// 修改订单状态 为已付款
					// 回复支付公司
					response.getWriter().print("success");
				}
				
				//修改订单状态
				Order order = orderService.getById(r6_Order);
				order.setState(Constant.ORDER_YIFUKUAN);
				
				orderService.update(order);
				
			} else {
				// 数据无效
				System.out.println("数据被篡改！");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			request.setAttribute("msg", "支付失败！");
		}
		return "/jsp/msg.jsp";
	}
	
}
