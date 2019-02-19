package ua.itea.models;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Cart {
	private Map<Product, Integer> products;
	private int size;
	
	public Cart() {
		size = 0;
		products = new HashMap<Product, Integer>();
	}
	
	public void addProduct(Product product) {
		Set<Product> set = products.keySet();
		Integer qnt = 1;
		if(set.contains(product)) {
			qnt = products.get(product) + 1;
		}
		products.put(product, qnt);
		size++;
	}
	
	public Map<Product, Integer> getProducts() {
		return products;
	}

	public void setProducts(Map<Product, Integer> products) {
		this.products = products;
	}

	public int getSize() {
		return size;
	}
}
