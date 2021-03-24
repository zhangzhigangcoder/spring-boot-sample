package com.core.proxy.jdk;

public class OrderServiceImpl implements OrderService {

	@Override
	public void add(Order o) {
		System.out.println("add a order" + o.toString());
	}

	@Override
	public void delete(String orderNo) {
		System.out.println(String.format("delete a order[orderNo=%s]", orderNo));
	}

}
