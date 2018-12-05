package com.gx.entity;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
/**
 * 购物车
 * @author Administrator
 *
 */
public class Cart {
	
	//购物项
	private Map<String,CartItem> itemMap = new HashMap<String, CartItem>();
	
	//总金额
	private Double total = 0.0;
	
	/**
	 * 获取所有的购物项
	 * 在前端页面上遍历时items="${cart.cartitems }"   
	 * 遍历的内容（cart.cartitems）是下面方法的get方法,注意首字母是小写
	 * @return
	 */
	public Collection<CartItem> getCartItems(){
		return itemMap.values();
	}

	public Map<String, CartItem> getItemMap() {
		return itemMap;
	}

	public void setItemMap(Map<String, CartItem> itemMap) {
		this.itemMap = itemMap;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}
	
	/**
	 * 加入购物车
	 * @param item
	 */
	public void add2card(CartItem item){
		//获取商品的ID
		String pid = item.getProduct().getPid();
		//1、判断购物车中是否有商品
		if(itemMap.containsKey(pid)){
			//有 修改数量 = 原来数量+新增数量
			CartItem oItem = itemMap.get(pid);
			oItem.setCount(oItem.getCount()+item.getCount());
		}else{
			 itemMap.put(pid, item);
		}
		//修改总金额
		total += item.getSubtotal();
	}
	
	/**
	 * 从购物车移除一个购物项
	 * @param pid
	 */
	public void removeFromCard(String pid){
		//1、从购物车（map）中移除一个购物项
		CartItem item = itemMap.remove(pid);
		//2、修改总金额
		total -= item.getSubtotal();
	}
	
	/**
	 * 清空购物车
	 */
	public void clearCard(){
		//1、清空map
		itemMap.clear();
		//2、修改总金额为0
		total = 0.0;
	}
	
}
