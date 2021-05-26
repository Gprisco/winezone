package model;

import java.util.Collection;
import java.util.Iterator;
import java.util.ArrayList;

public class CartBean {
	private Collection<WineBean> products;

	public CartBean() {
		products = new ArrayList<WineBean>();
	}

	public int getCount() {
		return products.size();
	}

	public int getTotalPrice() {
		int price = 0;
		Iterator<WineBean> iterator = products.iterator();

		while (iterator.hasNext()) {
			WineBean wine = iterator.next();
			price += wine.getPrice();
		}

		return price;
	}

	public Collection<WineBean> getProducts() {
		return products;
	}

	public void addToCart(WineBean product) {
		products.add(product);
	}

	public void removeFromCart(WineBean product) {
		products.remove(product);
	}

	public void empty() {
		this.products = new ArrayList<WineBean>();
	}
}
