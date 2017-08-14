package cn.itcast.shop.order.adminaction;

import java.util.List;

import cn.itcast.shop.order.service.OrderService;
import cn.itcast.shop.order.vo.Order;
import cn.itcast.shop.order.vo.OrderItem;
import cn.itcast.shop.utils.PageBean;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * ��̨��������Action
 * @author xkx
 *
 */
public class AdminOrderAction extends ActionSupport implements ModelDriven<Order>{

	//ģ����������Ҫ�Ķ���
	private Order order=new Order();

	public Order getModel() {
		return order;
	}
	//ע�붩��Service
	private OrderService orderService;
	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}
	
	//����page����
	private Integer page;
	public void setPage(Integer page) {
		this.page = page;
	}
	
	//����ҳ��ѯ���ж���
	public String findAll(){
		PageBean<Order> pageBean=orderService.findByPage(page);
		//ͨ��ֵջ�����ݴ���ҳ����
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findAll";
	}
	
	//���ݶ���id��ѯ������
	public String findOrderItem(){
		//���ݶ���id��ѯ������
		List<OrderItem> list=orderService.findOrderItem(order.getOid());
		//ͨ��ֵջ��ʾ��ҳ����
		ActionContext.getContext().getValueStack().set("list", list);
		return "findOrderItem";
	}
	
	//��̨�޸Ķ���״̬����
	public String updateState(){
		Order currOrder=orderService.findByOid(order.getOid());
		currOrder.setState(3);
		orderService.update(currOrder);
		return "updateStateSuccess";
	}
}
