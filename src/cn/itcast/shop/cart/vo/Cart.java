package cn.itcast.shop.cart.vo;

import java.io.Serializable;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 购物车项
 * @author xkx
 *
 */
public class Cart implements Serializable{//序列化接口
//购物车属性
	//购物项集合Map的key是商品的pid，value是购物项
	private Map<Integer, CartItem> map=new LinkedHashMap<Integer, CartItem>();
	//将map的value转成一个单列集合;;Cart对象中有一个cartItems属性
	public Collection<CartItem> getCartItems(){
		return map.values();
	}
	
	//购物总计
	private double total;
	
	public double getTotal() {
		return total;
	}

	//购物车功能
	//1.将购物项添加到购物车
	public void addCart(CartItem cartItem){
		//判断购物车中是否存在购物项
		//(存在：数量增加,总计=总计+购物项小计)
		//(不存在:向map中添加购物项,总计=总计+购物项小计)
	//1.获得商品pid
		Integer pid=cartItem.getProduct().getPid();
	//2.判断购物车是否存在该pid
		if(map.containsKey(pid)){
			//存在
			CartItem _cartItem = map.get(pid);//获得购物车中原来的购物项
			_cartItem.setCount(_cartItem.getCount()+cartItem.getCount());
		}else {
			//不存在
			map.put(pid, cartItem);
		}
		total+=cartItem.getSubtotal();
	}
	
	//2.从购物车中移除购物项
	public void removeCart(Integer pid){
		//将某个购物项移除购物车
		CartItem cartItem=map.remove(pid);
		//总计=总计-移除的小计
		total-=cartItem.getSubtotal();
	}
	
	//3.清空购物车
	public void clearCart(){
		//将购物项清空
		map.clear();
		//总计设置为0
		total=0;
	}
}
