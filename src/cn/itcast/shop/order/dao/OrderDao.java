package cn.itcast.shop.order.dao;

import java.util.List;

import javax.persistence.criteria.From;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.itcast.shop.order.vo.Order;
import cn.itcast.shop.order.vo.OrderItem;
import cn.itcast.shop.utils.PageHibernateCallback;

/**
 * 订单模块dao层代码实现
 * @author xkx
 *
 */
public class OrderDao extends HibernateDaoSupport{

	//dao层保存订单的方法
	public void save(Order order) {
		this.getHibernateTemplate().save(order);
		
	}

	//DAO层我的订单的总记录数
	public Integer findByCountUid(Integer uid) {
		String hql="select count(*) from Order o where o.user.uid=?";
		List<Long> list=this.getHibernateTemplate().find(hql,uid);	
		if(list!=null && list.size()>0){
			return list.get(0).intValue();
		}
		return null;
	}
	//DAO层我的订单的查询
	public List<Order> findByPageUid(Integer uid, Integer begin, Integer limit) {
		String hql="from Order o where o.user.uid=? order by ordertime desc";//查询到后按照订单时间倒序排序
		List<Order> list=this.getHibernateTemplate().execute(new PageHibernateCallback<Order>(hql,new Object[]{uid},begin, limit));
		return list;
	}

	//根据订单id查询订单
	public Order findByOid(Integer oid) {
		
		return this.getHibernateTemplate().get(Order.class,oid);
	}

	//DAO层实现修改订单的方法
	public void update(Order currOrder) {
		this.getHibernateTemplate().update(currOrder);
	}

	//dao统计订单个数的方法
	public int findByCount() {
		String hql="select count(*) from Order";
		List<Long> list=this.getHibernateTemplate().find(hql);
		if(list!=null && list.size()>0){
			return list.get(0).intValue();
		}
		return 0;
	}

	//DAO带分页查询的方法
	public List<Order> findByPage(int begin, int limit) {
		String hql="from Order order by ordertime desc";
		List<Order> list=this.getHibernateTemplate().execute(new PageHibernateCallback<Order>(hql, null,begin,limit));
		if(list!=null && list.size()>0){
			return list;
		}
		return null;
	}
	
	//DAO层根据订单id查询订单项
	public List<OrderItem> findOrderItem(Integer oid) {
		String hql="from OrderItem oi where oi.order.oid=?";
		List<OrderItem> list=this.getHibernateTemplate().find(hql,oid);
		if(list!=null && list.size()>0){
			return list;
		}
		return null;
	}

}
