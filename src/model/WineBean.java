package model;

import java.util.ArrayList;
import java.util.Collection;

public class WineBean {
	WinePrimaryKey pk;
	private Integer availability;
	private Integer price;
	private Integer wineryId;
	private Integer winefamilyId;
	private WinefamilyBean winefamily;
	private WineryBean winery;
	private Collection<WineWinegrapeBean> winegrapes;

	public WineBean() {
		this.winefamily = null;
		this.winery = null;
		this.winegrapes = new ArrayList<WineWinegrapeBean>();
	}

	public WinePrimaryKey getPk() {
		return pk;
	}

	public void setPk(WinePrimaryKey pk) {
		this.pk = pk;
	}

	public Integer getAvailability() {
		return availability;
	}

	public void setAvailability(Integer availability) {
		this.availability = availability;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public Integer getWineryId() {
		return wineryId;
	}

	public void setWineryId(Integer wineryId) {
		this.wineryId = wineryId;
	}

	public Integer getWinefamilyId() {
		return winefamilyId;
	}

	public void setWinefamilyId(Integer winefamilyId) {
		this.winefamilyId = winefamilyId;
	}

	public WinefamilyBean getWinefamily() {
		return winefamily;
	}

	public void setWinefamily(WinefamilyBean winefamily) {
		this.winefamily = winefamily;
	}

	public WineryBean getWinery() {
		return winery;
	}

	public void setWinery(WineryBean winery) {
		this.winery = winery;
	}

	public Collection<WineWinegrapeBean> getWinegrapes() {
		return winegrapes;
	}

	public void setWinegrapes(Collection<WineWinegrapeBean> winegrapes) {
		this.winegrapes = winegrapes;
	}

	public void addWinegrape(WineWinegrapeBean winegrape) {
		winegrapes.add(winegrape);
	}
}