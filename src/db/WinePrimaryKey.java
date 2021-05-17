package db;

public class WinePrimaryKey {
	private String wine;
	private int vintage;

	public WinePrimaryKey(String wine, int vintage) {
		this.wine = wine;
		this.vintage = vintage;
	}

	public String getWine() {
		return wine;
	}

	public int getVintage() {
		return vintage;
	}
}
