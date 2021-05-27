package model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

public class ShippingBean {
	private int id;
	private int idUser;
	private String address;
	private Date createdAt;
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

	public Date getCreatedAt() {
		return createdAt;
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

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	@Override
	public boolean equals(Object o) {
		// If the object is compared with itself then return true
		if (o == this) {
			return true;
		}

		/*
		 * Check if o is an instance of Complex or not "null instanceof [type]" also
		 * returns false
		 */
		if (!(o instanceof ShippingBean)) {
			return false;
		}

		// typecast o to Complex so that we can compare data members
		ShippingBean s = (ShippingBean) o;

		// Compare the data members and return accordingly
		return this.getId() == s.getId();
	}
}
