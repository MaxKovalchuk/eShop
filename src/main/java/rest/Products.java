package rest;

import java.util.ArrayList;
import java.util.List;

public class Products {
	private List<ProductRest> products;
	public Products() {
		// TODO Auto-generated constructor stub
	}
	public List<ProductRest> getProducts() {
		return products;
	}
	public void setProducts(List<ProductRest> products) {
		this.products = products;
	}
	@Override
	public String toString() {
		return "Products [products=" + products + "]";
	}
	
}
