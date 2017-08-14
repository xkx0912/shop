package cn.itcast.shop.order.dao;

import java.util.List;

import javax.persistence.criteria.From;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.itcast.shop.order.vo.Order;
import cn.itcast.shop.order.vo.OrderItem;
import cn.itcast.shop.utils.PageHibernateCallback;

/**
 * ����ģ��dao�����ʵ��
 * @author xkx
 *
 */
public class OrderDao extends HibernateDaoSupport{

	//dao�㱣�涩���ķ���
	public void save(Order order) {
		this.getHibernateTemplate().save(order);
		
	}

	//DAO���ҵĶ������ܼ�¼��
	public Integer findByCountUid(Integer uid) {
		String hql="select count(*) from Order o where o.user.uid=?";
		List<Long> list=this.getHibernateTemplate().find(hql,uid);	
		if(list!=null && list.size()>0){
			return list.get(0).intValue();
		}
		return null;
	}
	//DAO���ҵĶ����Ĳ�ѯ
	public List<Order> findByPageUid(Integer uid, Integer begin, Integer limit) {
		String hql="from Order o where o.user.uid=? order by ordertime desc";//��ѯ�����ն���ʱ�䵹������
		List<Order> list=this.getHibernateTemplate().execute(new PageHibernateCallback<Order>(hql,new Object[]{uid},begin, limit));
		return list;
	}

	//���ݶ���id��ѯ����
	public Order findByOid(Integer oid) {
		
		return this.getHibernateTemplate().get(Order.class,oid);
	}

	//DAO��ʵ���޸Ķ����ķ���
	public void update(Order currOrder) {
		this.getHibernateTemplate().update(currOrder);
	}

	//daoͳ�ƶ��������ķ���
	public int findByCount() {
		String hql="select count(*) from Order";
		List<Long> list=this.getHibernateTemplate().find(hql);
		if(list!=null && list.size()>0){
			return list.get(0).intValue();
		}
		return 0;
	}

	//DAO����ҳ��ѯ�ķ���
	public List<Order> findByPage(int begin, int limit) {
		String hql="from Order order by ordertime desc";
		List<Order> list=this.getHibernateTemplate().execute(new PageHibernateCallback<Order>(hql, null,begin,limit));
		if(list!=null && list.size()>0){
			return list;
		}
		return null;
	}
	
	//DAO����ݶ���id��ѯ������
	public List<OrderItem> findOrderItem(Integer oid) {
		String hql="from OrderItem oi where oi.order.oid=?";
		List<OrderItem> list=this.getHibernateTemplate().find(hql,oid);
		if(list!=null && list.size()>0){
			return list;
		}
		return null;
	}

}
