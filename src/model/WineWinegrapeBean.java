package model;

public class WineWinegrapeBean {
	private WineBean wine;
	private WinegrapeBean winegrape;
	private Integer percentage;

	public WineWinegrapeBean() {
	}

	public WineBean getWine() {
		return wine;
	}

	public void setWine(WineBean wine) {
		this.wine = wine;
	}

	public WinegrapeBean getWinegrape() {
		return winegrape;
	}

	public void setWinegrape(WinegrapeBean winegrape) {
		this.winegrape = winegrape;
	}

	public Integer getPercentage() {
		return percentage;
	}

	public void setPercentage(Integer percentage) {
		this.percentage = percentage;
	}
}