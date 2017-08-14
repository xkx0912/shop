package cn.itcast.shop.cart.vo;

import java.io.Serializable;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * ���ﳵ��
 * @author xkx
 *
 */
public class Cart implements Serializable{//���л��ӿ�
//���ﳵ����
	//�������Map��key����Ʒ��pid��value�ǹ�����
	private Map<Integer, CartItem> map=new LinkedHashMap<Integer, CartItem>();
	//��map��valueת��һ�����м���;;Cart��������һ��cartItems����
	public Collection<CartItem> getCartItems(){
		return map.values();
	}
	
	//�����ܼ�
	private double total;
	
	public double getTotal() {
		return total;
	}

	//���ﳵ����
	//1.����������ӵ����ﳵ
	public void addCart(CartItem cartItem){
		//�жϹ��ﳵ���Ƿ���ڹ�����
		//(���ڣ���������,�ܼ�=�ܼ�+������С��)
		//(������:��map����ӹ�����,�ܼ�=�ܼ�+������С��)
	//1.�����Ʒpid
		Integer pid=cartItem.getProduct().getPid();
	//2.�жϹ��ﳵ�Ƿ���ڸ�pid
		if(map.containsKey(pid)){
			//����
			CartItem _cartItem = map.get(pid);//��ù��ﳵ��ԭ���Ĺ�����
			_cartItem.setCount(_cartItem.getCount()+cartItem.getCount());
		}else {
			//������
			map.put(pid, cartItem);
		}
		total+=cartItem.getSubtotal();
	}
	
	//2.�ӹ��ﳵ���Ƴ�������
	public void removeCart(Integer pid){
		//��ĳ���������Ƴ����ﳵ
		CartItem cartItem=map.remove(pid);
		//�ܼ�=�ܼ�-�Ƴ���С��
		total-=cartItem.getSubtotal();
	}
	
	//3.��չ��ﳵ
	public void clearCart(){
		//�����������
		map.clear();
		//�ܼ�����Ϊ0
		total=0;
	}
}
