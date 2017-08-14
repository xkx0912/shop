package cn.itcast.shop.category.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.itcast.shop.category.vo.Category;

/**
 *  һ������־ò����
 * @author xkx
 *
 */
public class CategoryDao extends HibernateDaoSupport{
	
	//DAO��ѯ����һ������ķ���
	public List<Category> findAll() {
		String hql="from Category ";
		List<Category> List=this.getHibernateTemplate().find(hql);
		
		return List;
	}

	//DAO�����һ������ķ���
	public void save(Category category) {
		this.getHibernateTemplate().save(category);
	}

	//DAO���ѯһ������ķ���  Ȼ��ɾ��һ������(����cid)
	public Category findByCid(Integer cid) {
		
		return this.getHibernateTemplate().get(Category.class,cid);
	}

	//DAO����ݲ�ѯ���ɾ��һ������(����cid)
	public void delete(Category category) {
		this.getHibernateTemplate().delete(category);
	}

	//��̨�޸�һ������
	public void update(Category category) {
		this.getHibernateTemplate().update(category);
	}

}
