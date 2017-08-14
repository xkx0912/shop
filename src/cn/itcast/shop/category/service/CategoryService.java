package cn.itcast.shop.category.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import cn.itcast.shop.category.dao.CategoryDao;
import cn.itcast.shop.category.vo.Category;

/**
 * 一级分类业务层对象
 * @author xkx
 *
 */
@Transactional
public class CategoryService {
//注入categoryDao
	private CategoryDao categoryDao;

	public void setCategoryDao(CategoryDao categoryDao) {
		this.categoryDao = categoryDao;
	}

	//业务层查询所有一级分类的方法
	public List<Category> findAll() {
		
		return categoryDao.findAll();
	}

	//业务层添加一级分类的方法
	public void save(Category category) {
		categoryDao.save(category);
	}

	//业务层根据cid查询一级分类的方法
	public Category findByCid(Integer cid) {
		//调用Dao层
		return categoryDao.findByCid(cid);
	}

	//业务层删除以及分类
	public void delete(Category category) {
		categoryDao.delete(category);
	}

	//后台修改一级分类
	public void update(Category category) {
		categoryDao.update(category);
	}
	
}
