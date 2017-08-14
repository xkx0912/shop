package cn.itcast.shop.order.action;

import java.io.IOException;
import java.util.Date;

import org.apache.struts2.ServletActionContext;

import cn.itcast.shop.cart.vo.Cart;
import cn.itcast.shop.cart.vo.CartItem;
import cn.itcast.shop.order.service.OrderService;
import cn.itcast.shop.order.vo.Order;
import cn.itcast.shop.order.vo.OrderItem;
import cn.itcast.shop.user.vo.User;
import cn.itcast.shop.utils.PageBean;
import cn.itcast.shop.utils.PaymentUtil;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 订单管理Action
 * @author xkx
 *
 */
public class OrderAction extends ActionSupport implements ModelDriven<Order>{
	
	//模型驱动需要使用的对象
	private Order order=new Order();
	//注入OrderService
	private OrderService orderService;
	
	//接收page参数
	private Integer page;
	
	//支付通道编码
	private String pd_FrpId;
	
	//接收付款成功后的响应数据
	private String r6_Order;
	private String r3_Amt;
	
	public void setR6_Order(String r6_Order) {
		this.r6_Order = r6_Order;
	}

	public void setR3_Amt(String r3_Amt) {
		this.r3_Amt = r3_Amt;
	}

	public void setPd_FrpId(String pd_FrpId) {
		this.pd_FrpId = pd_FrpId;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}

	public Order getModel() {
		return order;
	}

	//生成订单的方法
	public String save(){
		//保存数据到数据库
		order.setOrdertime(new Date());
		order.setState(1);		// 1:未付款   2:订单已经付款,未发货    3:已经发货,未确认收获     4:订单结束,交易完成	
		//总计是购物车中的信息,session中cart获得
		Cart cart=(Cart) ServletActionContext.getRequest().getSession().getAttribute("cart");
		if(cart == null){
			this.addActionError("啊噢...还没购物,请先去购物!!");
			return "msg";
		}
		order.setTotal(cart.getTotal());
		//设置订单中的订单项
		 for(CartItem cartItem:cart.getCartItems()){
			 OrderItem orderItem=new OrderItem();
			 orderItem.setCount(cartItem.getCount());
			 orderItem.setSubtotal(cartItem.getSubtotal());
			 orderItem.setProduct(cartItem.getProduct());
			 orderItem.setOrder(order);
			 
			 order.getOrderItems().add(orderItem);
		 }
		 //订单所属的用户
		 User existUser = (User) ServletActionContext.getRequest().getSession().getAttribute("existUser");
		 if(existUser == null){
			 this.addActionError("您还没有登录,请先登录!");
			 return "login";
		 }
		order.setUser(existUser);
		orderService.save(order); 
		//将订单对象显示到页面
		//通过值栈的方式来进行显示:因为Order显示的对象是模型驱动使用的对象
		//提交订单后清空购物车
		cart.clearCart();
		return "saveSuccess";
	}
	//我的订单查询
	public String findByUid(){
		User user=(User) ServletActionContext.getRequest().getSession().getAttribute("existUser");
		//调用Service查询
		PageBean<Order> pageBean=orderService.findByPageUid(user.getUid(),page);
		//将分页的数据显示到页面上
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);//将分页数据存入到值栈中
		return "findByUidSuccess";
	}
	
	//根据订单id查询订单方法
	public String findByOid(){
		order=orderService.findByOid(order.getOid());
		return "findByOidSuccess";
	}
	
	//为订单付款方法
	public String payOrder() throws IOException{
		// 1.修改数据:
		Order currOrder = orderService.findByOid(order.getOid());
		currOrder.setAddr(order.getAddr());
		currOrder.setName(order.getName());
		currOrder.setPhone(order.getPhone());
		// 修改订单
		orderService.update(currOrder);			
		
		//2.为订单付款
		// 付款需要的参数:
		String p0_Cmd = "Buy"; // 业务类型:
		String p1_MerId = "10001126856";// 商户编号:
		String p2_Order = order.getOid().toString();// 订单编号:
		String p3_Amt = "0.01"; // 付款金额:
		String p4_Cur = "CNY"; // 交易币种:
		String p5_Pid = ""; // 商品名称:
		String p6_Pcat = ""; // 商品种类:
		String p7_Pdesc = ""; // 商品描述:
		String p8_Url = "http://localhost:8080/shop/order_callBack.action"; // 商户接收支付成功数据的地址:
		String p9_SAF = ""; // 送货地址:
		String pa_MP = ""; // 商户扩展信息:	
		String pd_FrpId=this.pd_FrpId;//支付通道编码
		String pr_NeedResponse = "1"; // 应答机制:
		String keyValue = "69cl522AV6q613Ii4W6u8K6XuW8vM1N6bFgyv769220IuYe9u37N4y7rI4Pl"; // 秘钥
		String hmac = PaymentUtil.buildHmac(p0_Cmd, p1_MerId, p2_Order, p3_Amt,
				p4_Cur, p5_Pid, p6_Pcat, p7_Pdesc, p8_Url, p9_SAF, pa_MP,
				pd_FrpId, pr_NeedResponse, keyValue); // hmac
		//向易宝支付出发
		StringBuffer sb=new StringBuffer("https://www.yeepay.com/app-merchant-proxy/node?");
		sb.append("p0_Cmd=").append(p0_Cmd).append("&");
		sb.append("p1_MerId=").append(p1_MerId).append("&");
		sb.append("p2_Order=").append(p2_Order).append("&");
		sb.append("p3_Amt=").append(p3_Amt).append("&");
		sb.append("p4_Cur=").append(p4_Cur).append("&");
		sb.append("p5_Pid=").append(p5_Pid).append("&");
		sb.append("p6_Pcat=").append(p6_Pcat).append("&");
		sb.append("p7_Pdesc=").append(p7_Pdesc).append("&");
		sb.append("p8_Url=").append(p8_Url).append("&");
		sb.append("p9_SAF=").append(p9_SAF).append("&");
		sb.append("pa_MP=").append(pa_MP).append("&");
		sb.append("pd_FrpId=").append(pd_FrpId).append("&");
		sb.append("pr_NeedResponse=").append(pr_NeedResponse).append("&");
		sb.append("hmac=").append(hmac);
		
		//重定向到易宝
		ServletActionContext.getResponse().sendRedirect(sb.toString());
		return NONE;
	}
	
	//付款成功后的转向页面
	public String callBack(){
		//付款后  修改订单状态为:已付款
		Order currOrder=orderService.findByOid(Integer.parseInt(r6_Order));
		currOrder.setState(2);
		orderService.update(currOrder);
		//在页面显示付款成功信息
		this.addActionMessage("订单付款成功!订单编号:"+r6_Order+"\r 付款金额:"+r3_Amt);
		return "msg";
	}
	
	//前台确认收货
	public String updateState(){
		//根据传过来的oid查询订单
		Order currOrder=orderService.findByOid(order.getOid());
		//修改订单状态为(4)交易完成(4)
		currOrder.setState(4);
		orderService.update(currOrder);
		return "updateStateSuccess";
	}
	
}
