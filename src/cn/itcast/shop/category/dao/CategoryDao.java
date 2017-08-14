package cn.itcast.shop.category.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.itcast.shop.category.vo.Category;

/**
 *  一级分类持久层对象
 * @author xkx
 *
 */
public class CategoryDao extends HibernateDaoSupport{
	
	//DAO查询所有一级分类的方法
	public List<Category> findAll() {
		String hql="from Category ";
		List<Category> List=this.getHibernateTemplate().find(hql);
		
		return List;
	}

	//DAO层添加一级分类的方法
	public void save(Category category) {
		this.getHibernateTemplate().save(category);
	}

	//DAO层查询一级分类的方法  然后删除一级分类(根据cid)
	public Category findByCid(Integer cid) {
		
		return this.getHibernateTemplate().get(Category.class,cid);
	}

	//DAO层根据查询结果删除一级分类(根据cid)
	public void delete(Category category) {
		this.getHibernateTemplate().delete(category);
	}

	//后台修改一级分类
	public void update(Category category) {
		this.getHibernateTemplate().update(category);
	}

}
