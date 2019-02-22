package ua.itea.models;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Cart {
	private Map<Product, Integer> products;
	private int size;
	private int totalCost;
	
	public Cart() {
		size = 0;
		products = new HashMap<Product, Integer>();
	}
	
	public Map<Product, Integer> getProducts() {
		return products;
	}

	public void setProducts(Map<Product, Integer> products) {
		this.products = products;
	}
	
	public void setSize(int size) {
		this.size = size;
	}

	public int getSize() {
		return size;
	}

	public int getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(int totalCost) {
		this.totalCost = totalCost;
	}
}
