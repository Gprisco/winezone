package model;

import java.util.ArrayList;
import java.util.Collection;

public class ShippingBean {
	private int id;
	private int idUser;
	private String address;
	
	private Collection<ShippingWineBean> wines;

	public ShippingBean() {
		this.wines = new ArrayList<ShippingWineBean>();
	}

	public int getId() {
		return id;
	}

	public int getIdUser() {
		return idUser;
	}

	public String getAddress() {
		return address;
	}

	public Collection<ShippingWineBean> getWines() {
		return wines;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void addWine(ShippingWineBean wine) {
		this.wines.add(wine);
	}
}
