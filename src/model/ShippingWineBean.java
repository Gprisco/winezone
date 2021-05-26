package model;

public class ShippingWineBean {
	private int id;

	private String wine;
	private int vintage;
	private int idShipping;

	public ShippingWineBean() {
	}

	public int getId() {
		return id;
	}

	public String getWine() {
		return wine;
	}

	public int getVintage() {
		return vintage;
	}

	public int getIdShipping() {
		return idShipping;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setWine(String wine) {
		this.wine = wine;
	}

	public void setVintage(int vintage) {
		this.vintage = vintage;
	}

	public void setIdShipping(int idShipping) {
		this.idShipping = idShipping;
	}
}
