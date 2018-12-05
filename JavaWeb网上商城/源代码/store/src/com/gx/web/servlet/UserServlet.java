package com.gx.web.servlet;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.gx.constant.Constant;
import com.gx.entity.User;
import com.gx.service.UserService;
import com.gx.service.Impl.UserServiceImpl;
import com.gx.utils.UUIDUtils;
import com.gx.web.servlet.base.BaseServlet;

/**
 * 用户模块
 */
public class UserServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	UserService userService = new UserServiceImpl();
	
	@Override
	public String index(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		return super.index(request, response);
	}
	
	/**
	 * 用户退出
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String logout(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getSession().invalidate();
		response.sendRedirect(request.getContextPath());
		return super.index(request, response);
	}
	
	/**
	 * 用户登录
	 */
	public String login(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			//1、获取用户名和密码
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			//2、调用service完成登录，返回user
			User user = userService.login(username,password);
			//3、判断user，根据结果生成提示
			if(user == null){
				//用户名密码不匹配<font color='red' >用户名和密码不匹配！</font>
				request.setAttribute("msg", "用户名和密码不匹配！");
				return "/jsp/login.jsp";
			}
			//若用户名不为空继续判断是否激活
			if(Constant.USER_IS_ACTIVE != user.getState()){
				//未激活
				request.setAttribute("msg", "请先去邮箱激活，再登陆！");
				return "/jsp/msg.jsp";
			}
			//登录成功，保存用户登录状态
			request.getSession().setAttribute("user",user);
			
			//——————————————记住用户名————————————————
			//判断是否勾选了记住用户名
			String nameok = request.getParameter("savename");
			if(Constant.SAVE_NAME.equals(nameok)){
				Cookie cookie = new Cookie("saveName", URLEncoder.encode(username,"utf-8"));
				cookie.setMaxAge(Integer.MAX_VALUE);
				cookie.setPath(request.getContextPath()+"/");
				
				response.addCookie(cookie);
			}
			
			
			//跳转到index.jsp
			response.sendRedirect(request.getContextPath());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			request.setAttribute("msg", "用户登录失败！");
			return "/jsp/msg.jsp";
		}
		return null;
	}
	
	/**
	 * 跳转到登录页面
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String loginUI(HttpServletRequest request, HttpServletResponse response) {
		return "/jsp/login.jsp";
	}
	
	/**
	 * 用户激活
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String active(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			//1、接受code
			String code = request.getParameter("code");
			//2、调用service	完成激活，返回user
			User user = userService.active(code);
			//3、判断user，生成不同的信息
			if(user == null){
				//没有找到这个用户，激活失败
				request.setAttribute("msg", "请重新激活或重新注册！");
				return "/jsp/msg.jsp";
			}
			//激活成功
			request.setAttribute("msg", "恭喜你，激活成功，可以登录了……");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			request.setAttribute("msg", "请重新激活或重新注册！");
			return "/jsp/msg.jsp";
		}
		return "/jsp/msg.jsp";
	}
	
	
	/**
	 * 用户注册
	 * @param request
	 * @param response
	 * @return
	 */
	public String regist(HttpServletRequest request, HttpServletResponse response){
		// TODO Auto-generated method stub
		try {
			//1、封装对象
			User user = new User();
			BeanUtils.populate(user, request.getParameterMap());
			//1.1手动封装UID
			user.setUid(UUIDUtils.getId());
			//1.2手动封装激活状态state
			user.setState(Constant.USER_IS_NOT_ACTIVE);
			//1.3手动封装激活码code
			user.setCode(UUIDUtils.getCode());
			//2、调用service完成注册
			userService.regist(user);
			//3、页面转发，提示信息
			request.setAttribute("msg", "恭喜你注册成功！请登录邮箱激活！");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			//转发到msg.jsp
			request.setAttribute("msg", "用户注册失败！");
			return "/jsp/msg.jsp";
		}
		return "/jsp/msg.jsp";
	}
	
	/**
	 * 跳转到注册页面
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String registUI(HttpServletRequest request, HttpServletResponse response) {
		return "/jsp/register.jsp";
	}
}
