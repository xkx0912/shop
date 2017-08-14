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
 * 后台订单管理Action
 * @author xkx
 *
 */
public class AdminOrderAction extends ActionSupport implements ModelDriven<Order>{

	//模型驱动所需要的对象
	private Order order=new Order();

	public Order getModel() {
		return order;
	}
	//注入订单Service
	private OrderService orderService;
	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}
	
	//接收page参数
	private Integer page;
	public void setPage(Integer page) {
		this.page = page;
	}
	
	//带分页查询所有订单
	public String findAll(){
		PageBean<Order> pageBean=orderService.findByPage(page);
		//通过值栈将数据带到页面上
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findAll";
	}
	
	//根据订单id查询订单项
	public String findOrderItem(){
		//根据订单id查询订单项
		List<OrderItem> list=orderService.findOrderItem(order.getOid());
		//通过值栈显示到页面上
		ActionContext.getContext().getValueStack().set("list", list);
		return "findOrderItem";
	}
	
	//后台修改订单状态方法
	public String updateState(){
		Order currOrder=orderService.findByOid(order.getOid());
		currOrder.setState(3);
		orderService.update(currOrder);
		return "updateStateSuccess";
	}
}
