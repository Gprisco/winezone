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
		if (!(o instanceof WinePrimaryKey)) {
			return false;
		}

		// typecast o to Complex so that we can compare data members
		WinePrimaryKey w = (WinePrimaryKey) o;

		// Compare the data members and return accordingly
		return this.getWine().equals(w.getWine()) && this.getVintage() == w.getVintage();
	}
}
