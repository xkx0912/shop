package cn.itcast.shop.product.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.sun.org.apache.bcel.internal.generic.Select;

import cn.itcast.shop.product.vo.Product;
import cn.itcast.shop.utils.PageHibernateCallback;

/**
 * ��Ʒ�־ò����
 * @author xkx
 *
 */
public class ProductDao extends HibernateDaoSupport {

	//��ҳ��������Ʒ��ѯ
	public List<Product> findHot() {
		//ʹ������������ѯ
		DetachedCriteria criteria=DetachedCriteria.forClass(Product.class);
		//��ѯ������Ʒ  ����ʽis_hot=1
		criteria.add(Restrictions.eq("is_hot", 1));
		//��������������Ʒ�������
		criteria.addOrder(Order.desc("pdate"));
		//ִ�в�ѯ
		List<Product> list=this.getHibernateTemplate().findByCriteria(criteria, 0, 10);
		return list;
	}

	//��ҳ��������Ʒ��ѯ
	public List<Product> findNew() {
		//ʹ������������ѯ
		DetachedCriteria criteria=DetachedCriteria.forClass(Product.class);
		//��������������Ʒ�������
		criteria.addOrder(Order.desc("pdate"));
		List<Product> list=this.getHibernateTemplate().findByCriteria(criteria,0,10);
		return list;
	}

	//������Ʒ��pid��ѯ��Ʒ��Ϣ
	public Product findByPid(Integer pid) {
		
		return this.getHibernateTemplate().get(Product.class, pid);
	}

	//������Ʒ�ķ���id��ѯ��Ʒ�ĸ���
	public int findCountCid(Integer cid) {
		String hql="select count(*) from Product p where p.categorySecond.category.cid=?";
		List<Long> list=this.getHibernateTemplate().find(hql,cid);
		if(list!=null && list.size()>0){
			return list.get(0).intValue();
		}
		return 0;
	}

	//���ݷ����id��ѯ��Ʒ�ļ���
	public List<Product> findByPageCid(Integer cid, int begin, int limit) {
		//String hql="select p.* from category c,categorysecond cs,product p where c.cid = cs.cid and cs.csid = p.csid and c.cid = 2";
		//select p from Category c,CategorySecond cs,Product p where c.cid = cs.category.cid and cs.csid = p.categorySecond.csid and c.cid = ? 		//hqlд��
		String hql="select p from  Product p join p.categorySecond cs join cs.category c where c.cid=?";
		//��ҳ����һ��д��
		List<Product> list= this.getHibernateTemplate().execute(new PageHibernateCallback<Product>(hql, new Object[]{cid}, begin, limit));
		if(list!=null && list.size()>0){
			return list;
		}
		return null;
	}

	//���ݶ��������ѯ����
	public int findCountCsid(Integer csid) {
		String hql="select count(*) from Product p where p.categorySecond.csid=?";
		List<Long> list=this.getHibernateTemplate().find(hql,csid);
		if(list!=null && list.size()>0){
			return list.get(0).intValue();
		}
		return 0;
	}

	//���ݶ��������csid��ѯ��Ʒ
	public List<Product> findByPageCsid(Integer csid, int begin, int limit) {
		String hql="select p from Product p join p.categorySecond cs where cs.csid=?";
		List<Product> list=this.getHibernateTemplate().execute(new PageHibernateCallback<Product>(hql, new Object[]{csid}, begin, limit));
		if(list!=null && list.size()>0){
			return list;
		}
		return null;
	}

	//DAOͳ��������Ʒ����
	public int findCount() {
		String hql="select count(*) from Product";
		List<Long> list=this.getHibernateTemplate().find(hql);
		if(list!=null && list.size()>0){
			return list.get(0).intValue();
		}
		return 0;
	}

	public List<Product> findByPage(int begin, int limit) {
		String hql="from Product order by pdate desc";
		List<Product> list=this.getHibernateTemplate().execute(new PageHibernateCallback<Product>(hql, null,begin,limit));
		if(list!=null && list.size()>0){
			return list;
		}
		return null;
	}

	//DAO�㱣����Ʒ�ķ���
	public void save(Product product) {
		this.getHibernateTemplate().save(product);
	}

	//DAO��ɾ����Ʒ�ķ���
	public void delete(Product product) {
		this.getHibernateTemplate().delete(product);
	}

	//DAO�������Ʒ���޸�
	public void update(Product product) {
	this.getHibernateTemplate().update(product);
	}

}