package io.javabrains.convic19tracker.models;

public class LocationStats {
	
	private String state;
	private String country;
	private int lastestTotalCases;
	private int diffFromPreviousStats;
	
	public int getDiffFromPreviousStats() {
		return diffFromPreviousStats;
	}
	public void setDiffFromPreviousStats(int diffFromPreviousStats) {
		this.diffFromPreviousStats = diffFromPreviousStats;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public int getLastestTotalCases() {
		return lastestTotalCases;
	}
	public void setLastestTotalCases(int lastestTotalCases) {
		this.lastestTotalCases = lastestTotalCases;
	}
	@Override
	public String toString() {
		return "LocationStats [state=" + state + ", country=" + country + ", lastestTotalCases=" + lastestTotalCases
				+ "]";
	}
	
	

}
