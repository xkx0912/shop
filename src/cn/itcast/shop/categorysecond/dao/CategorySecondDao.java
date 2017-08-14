package cn.itcast.shop.categorysecond.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.itcast.shop.categorysecond.vo.CategorySecond;
import cn.itcast.shop.utils.PageHibernateCallback;

/**
 * ��������DAO��
 * @author xkx
 *
 */
public class CategorySecondDao extends HibernateDaoSupport{

	//DAO��ͳ�ƶ��������¼���ķ���
	public int findCount() {
		String hql="select count(*) from CategorySecond";
		List<Long> list=this.getHibernateTemplate().find(hql);
		if(list !=null && list.size()>0){
			return list.get(0).intValue();
		}
		return 0;
	}

	//DAO���ҳ��ѯ��������ķ���
	public List<CategorySecond> findByPage(int begin, Integer limit) {
		String hql="from CategorySecond order by csid desc";
		List<CategorySecond> list=this.getHibernateTemplate().execute(new PageHibernateCallback<CategorySecond>(hql,null, begin,limit));
		if(list !=null && list.size()>0){
			return list;
		}
		return null;
	}

	//DAO�㱣���������ķ���
	public void save(CategorySecond categorySecond) {
		this.getHibernateTemplate().save(categorySecond);
	}

	//DAO����ݶ�������id��ѯ����������
	public CategorySecond findByCsid(Integer csid) {
		return this.getHibernateTemplate().get(CategorySecond.class,csid);
	}
	//DAO��ɾ����������ķ���ʵ��
	public void delete(CategorySecond categorySecond) {
		this.getHibernateTemplate().delete(categorySecond);
	}

	//DAO��ʵ�ֶ��������޸ķ���
	public void update(CategorySecond categorySecond) {
		this.getHibernateTemplate().update(categorySecond);
	}

	//DAO���ѯ���ж������෽��
	public List<CategorySecond> findAll() {
		String hql="from CategorySecond";
		return this.getHibernateTemplate().find(hql);
	}

	

}
