package com.gx.service.Impl;

import com.gx.constant.Constant;
import com.gx.dao.UserDao;
import com.gx.dao.Impl.UserDaoImpl;
import com.gx.entity.User;
import com.gx.service.UserService;
import com.gx.utils.BeanFactory;
import com.gx.utils.MailUtils;

public class UserServiceImpl implements UserService {
	//UserDao userDao = new UserDaoImpl();
	UserDao userDao = (UserDao) BeanFactory.getBean("UserDao");

	/**
	 * 用户注册
	 * @throws Exception 
	 */
	public void regist(User user) throws Exception {
		//1、调用dao完成注册
		userDao.save(user);
		//2、发送激活邮件
		String emailMsg = "恭喜"+user.getName()+":成为吖吖商城的会员，<a href='http://127.0.0.1:8080/store/user?method=active&code="+user.getCode()+"'>点此链接激活</a>";
		MailUtils.sendMail(user.getEmail(), emailMsg);
	}

	/**
	 * 用户激活
	 * @throws Exception 
	 */
	public User active(String code) throws Exception {
		// TODO Auto-generated method stub
		//1、通过code获得用户
		User user = userDao.getByCode(code);
		//1.1通过激活码没找到用户
		if(user == null){
			return null;
		}
		//2、若获取到了修改用户
		user.setState(Constant.USER_IS_ACTIVE);
		user.setCode(null);
		userDao.update(user);
		return user;
	}

	/**
	 * 用户登录
	 */
	public User login(String username, String password) throws Exception {
		return userDao.getByUsernameAndPwd(username,password);
	}

}
