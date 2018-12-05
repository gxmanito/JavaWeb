package com.gx.dao.Impl;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import com.gx.dao.UserDao;
import com.gx.entity.User;
import com.gx.utils.DataSourceUtils;

public class UserDaoImpl implements UserDao {
	QueryRunner queryRunner = new QueryRunner(DataSourceUtils.getDataSource());
	
	//用户注册
	public void save(User user) throws SQLException {
		// TODO Auto-generated method stub
		String sql="insert into user values(?,?,?,?,?,?,?,?,?,?)";
		queryRunner.update(sql, user.getUid(),user.getUsername(),user.getPassword(),
				user.getName(),user.getEmail(),user.getTelephone(),user.getBirthday(),user.getSex(),user.getState(),user.getCode());
	}

	/**
	 * 通过激活码获取用户
	 */
	public User getByCode(String code) throws Exception {
		String sql = "select * from user where code = ? limit 1";
		return queryRunner.query(sql, new BeanHandler<User>(User.class),code);
	}

	/**
	 * 更新用户
	 */
	public void update(User user) throws Exception {
		String sql = "update user set password=?,sex=?,state=?,code=? where uid=?";
		queryRunner.update(sql,user.getPassword(),user.getSex(),user.getState(),user.getCode(),user.getUid());
	}

	/**
	 * 用户登录
	 */
	public User getByUsernameAndPwd(String username, String password) throws Exception {
		String sql = "select * from user where username = ? and password = ? limit 1";
		return queryRunner.query(sql, new BeanHandler<User>(User.class),username,password);
	}

}
