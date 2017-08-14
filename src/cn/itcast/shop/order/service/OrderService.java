package cn.itcast.shop.order.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import cn.itcast.shop.order.dao.OrderDao;
import cn.itcast.shop.order.vo.Order;
import cn.itcast.shop.order.vo.OrderItem;
import cn.itcast.shop.utils.PageBean;

/**
 * ����ģ��ҵ���(service)����ʵ��
 * @author xkx
 *
 */
@Transactional
public class OrderService {

	//ע��OrderDao
	private OrderDao orderDao;

	public void setOrderDao(OrderDao orderDao) {
		this.orderDao = orderDao;
	}

	//���涩����ҵ���
	public void save(Order order) {
		
		orderDao.save(order);
	}

	//��ѯ�ҵĶ���ҵ������
	public PageBean<Order> findByPageUid(Integer uid, Integer page) {
		PageBean<Order> pageBean=new PageBean<Order>();
		//���õ�ǰҳ��
		pageBean.setPage(page);
		//����ÿһҳ��ʾ�ĸ���
		Integer limit=5;
		pageBean.setLimit(limit);
		//�ܼ�¼��
		Integer totalCount=null;
		totalCount=orderDao.findByCountUid(uid);
		pageBean.setTotalCount(totalCount);
		//��ҳ��
		Integer totalPage=null;
		if(totalCount%limit==0){
			totalPage=totalCount/limit;
		}else {
			totalPage=totalCount/limit+1;
		}
		pageBean.setTotalPage(totalPage);
		//����ÿҳ��ʾ���ݵļ���
		Integer begin=(page-1)*limit;
		List<Order> list=orderDao.findByPageUid(uid,begin,limit);
		pageBean.setList(list);	
		return pageBean;
	}

	//���ݶ���id��ѯ����
	public Order findByOid(Integer oid) {
		return orderDao.findByOid(oid);
	}

	//ҵ����޸Ķ�������
	public void update(Order currOrder) {
		orderDao.update(currOrder);
	}

	//��̨ҵ����ѯ�����ķ�ҳ����
	public PageBean<Order> findByPage(Integer page) {
		PageBean<Order> pageBean=new PageBean<Order>();
		//���õ�ǰҳ��
		pageBean.setPage(page);
		//����ÿҳ��ʾ����Ʒ����
		int limit=10;
		pageBean.setLimit(limit);
		//�ܼ�¼��
		int totalCount =0;
		totalCount=orderDao.findByCount();
		pageBean.setTotalCount(totalCount);
		//��ҳ��
		int totalPage=0;
		//totalPage=(int) Math.ceil(totalCount/limit);
		if(totalCount % limit == 0){
			totalPage=totalCount/limit;
		}else {
			totalPage=totalCount/limit+1;
		}
		pageBean.setTotalPage(totalPage);
		//ÿҳ��ʾ�����ݵļ���:
		//���Ŀ�ʼ:
		int begin=(page-1)*limit;
		List<Order> list=orderDao.findByPage(begin, limit);
		pageBean.setList(list);
		return pageBean;
	}

	//ҵ�����ݶ���id��ѯ������	
	public List<OrderItem> findOrderItem(Integer oid) {
		return orderDao.findOrderItem(oid);
	}
	
}
