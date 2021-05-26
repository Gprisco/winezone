package model;

public final class WineWinegrapePrimaryKey {
	private int winegrapeId;
	private WinePrimaryKey winePk;

	public WineWinegrapePrimaryKey(int winegrapeId, WinePrimaryKey winePk) {
		this.winegrapeId = winegrapeId;
		this.winePk = winePk;
	}

	public int getWinegrapeId() {
		return winegrapeId;
	}

	public WinePrimaryKey getWinePk() {
		return winePk;
	}
}
