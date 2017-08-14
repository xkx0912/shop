package cn.itcast.shop.order.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import cn.itcast.shop.order.dao.OrderDao;
import cn.itcast.shop.order.vo.Order;
import cn.itcast.shop.order.vo.OrderItem;
import cn.itcast.shop.utils.PageBean;

/**
 * 订单模块业务层(service)代码实现
 * @author xkx
 *
 */
@Transactional
public class OrderService {

	//注入OrderDao
	private OrderDao orderDao;

	public void setOrderDao(OrderDao orderDao) {
		this.orderDao = orderDao;
	}

	//保存订单的业务层
	public void save(Order order) {
		
		orderDao.save(order);
	}

	//查询我的订单业务层代码
	public PageBean<Order> findByPageUid(Integer uid, Integer page) {
		PageBean<Order> pageBean=new PageBean<Order>();
		//设置当前页数
		pageBean.setPage(page);
		//设置每一页显示的个数
		Integer limit=5;
		pageBean.setLimit(limit);
		//总记录数
		Integer totalCount=null;
		totalCount=orderDao.findByCountUid(uid);
		pageBean.setTotalCount(totalCount);
		//总页数
		Integer totalPage=null;
		if(totalCount%limit==0){
			totalPage=totalCount/limit;
		}else {
			totalPage=totalCount/limit+1;
		}
		pageBean.setTotalPage(totalPage);
		//设置每页显示数据的集合
		Integer begin=(page-1)*limit;
		List<Order> list=orderDao.findByPageUid(uid,begin,limit);
		pageBean.setList(list);	
		return pageBean;
	}

	//根据订单id查询订单
	public Order findByOid(Integer oid) {
		return orderDao.findByOid(oid);
	}

	//业务层修改订单代码
	public void update(Order currOrder) {
		orderDao.update(currOrder);
	}

	//后台业务层查询订单的分页方法
	public PageBean<Order> findByPage(Integer page) {
		PageBean<Order> pageBean=new PageBean<Order>();
		//设置当前页数
		pageBean.setPage(page);
		//设置每页显示的商品数量
		int limit=10;
		pageBean.setLimit(limit);
		//总记录数
		int totalCount =0;
		totalCount=orderDao.findByCount();
		pageBean.setTotalCount(totalCount);
		//总页数
		int totalPage=0;
		//totalPage=(int) Math.ceil(totalCount/limit);
		if(totalCount % limit == 0){
			totalPage=totalCount/limit;
		}else {
			totalPage=totalCount/limit+1;
		}
		pageBean.setTotalPage(totalPage);
		//每页显示的数据的集合:
		//从哪开始:
		int begin=(page-1)*limit;
		List<Order> list=orderDao.findByPage(begin, limit);
		pageBean.setList(list);
		return pageBean;
	}

	//业务层根据订单id查询订单项	
	public List<OrderItem> findOrderItem(Integer oid) {
		return orderDao.findOrderItem(oid);
	}
	
}
