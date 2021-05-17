package db;

import db.Helpers.Winecolor;
import db.Helpers.Winedenom;
import db.Helpers.Winetype;

public class WinefamilyBean {
	private Integer winefamilyId;
	private String winefamily;
	private Winecolor winecolor;
	private Winetype winetype;
	private Winedenom winedenom;
	private Integer regionId;

	public WinefamilyBean() {
	}

	public Integer getWinefamilyId() {
		return winefamilyId;
	}

	void setWinefamilyId(Integer winefamilyId) {
		this.winefamilyId = winefamilyId;
	}

	public String getWinefamily() {
		return winefamily;
	}

	void setWinefamily(String winefamily) {
		this.winefamily = winefamily;
	}

	public Winecolor getWinecolor() {
		return winecolor;
	}

	void setWinecolor(Winecolor winecolor) {
		this.winecolor = winecolor;
	}

	public Winetype getWinetype() {
		return winetype;
	}

	void setWinetype(Winetype winetype) {
		this.winetype = winetype;
	}

	public Winedenom getWinedenom() {
		return winedenom;
	}

	void setWinedenom(Winedenom winedenom) {
		this.winedenom = winedenom;
	}

	public Integer getRegionId() {
		return regionId;
	}

	void setRegionId(Integer regionId) {
		this.regionId = regionId;
	}
}