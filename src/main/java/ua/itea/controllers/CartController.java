package ua.itea.controllers;

import java.util.Iterator;
import java.util.Set;

import ua.itea.models.Cart;
import ua.itea.models.Product;

public class CartController {

	public static void addProduct(Cart cart, Product product, Integer qnt) {
		Set<Product> set = cart.getProducts().keySet();
		if (set.contains(product)) {
			qnt = cart.getProducts().get(product) + qnt;
		}
		cart.getProducts().put(product, qnt);
		cart.setSize(cart.getSize() + 1);
		CartController.setTotalCost(cart);
	}

	public static void removeProduct(Cart cart, Product product) {
		Set<Product> set = cart.getProducts().keySet();
		Integer qnt = cart.getProducts().get(product);
		if (set.contains(product)) {
			if (qnt > 1) {
				qnt = cart.getProducts().get(product) - 1;
				cart.getProducts().put(product, qnt);
			} else {
				removeProductFully(cart, product);
			}
			cart.setSize(cart.getSize() - 1);
			CartController.setTotalCost(cart);
		}
	}

	public static void setTotalCost(Cart cart) {
		Set<Product> set = cart.getProducts().keySet();
		int total = 0;
		for (Product product : set) {
			total += product.getPrice() * cart.getProducts().get(product);
		}
		cart.setTotalCost(total);
	}

	public static void removeProductFully(Cart cart, Product product) {
		cart.getProducts().remove(product);
	}

	public static void cleanCart(Cart cart) {
		Set<Product> set = cart.getProducts().keySet();
		Iterator it = set.iterator();
		while (it.hasNext()) {
			Product product = (Product) it.next();
			it.remove();
		}
		cart.setSize(0);
		cart.setTotalCost(0);
	}
}
