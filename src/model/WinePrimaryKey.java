package model;

public final class WinePrimaryKey {
	private String wine;
	private int vintage;

	public WinePrimaryKey(String wine, int vintage) {
		this.wine = wine;
		this.vintage = vintage;
	}

	public String getWine() {
		return wine.substring(0, 1).toUpperCase() + wine.substring(1);
	}

	public int getVintage() {
		return vintage;
	}
}
