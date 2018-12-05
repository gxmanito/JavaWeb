package com.gx.dao.Impl;

import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.gx.dao.CategoryDao;
import com.gx.entity.Category;
import com.gx.utils.DataSourceUtils;

public class CategoryDaoImpl implements CategoryDao {
	QueryRunner queryRunner = new QueryRunner(DataSourceUtils.getDataSource());

	/**
	 * 查询所有分类
	 */
	public List<Category> findAll() throws Exception {
		String sql = "select * from category";
		return queryRunner.query(sql, new BeanListHandler<Category>(Category.class));
	}

	/**
	 * 添加分类
	 */
	public void save(Category category) throws Exception {
		String sql = "insert into category values(?,?) ";
		queryRunner.update(sql,category.getCid(),category.getCname());
	}

	/**
	 * 后台删除分类
	 */
	public void del(String cid) throws Exception {
		String sql = "delete from category where cid = ? ";
		queryRunner.update(sql,cid);
	}

	/**
	 * 根据ID查询分类信息
	 */
	public Category getUpdId(String cid) throws Exception {
		String sql = "select * from category where cid=?";
		return queryRunner.query(sql, new BeanHandler<Category>(Category.class),cid);
	}

	/**
	 * 修改分类
	 */
	public void upd(Category category) throws Exception {
		String sql = "update  category set cname = ? where cid=?";
		queryRunner.update(sql,category.getCname(),category.getCid());
	}

}
