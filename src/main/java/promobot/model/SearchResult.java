package promobot.model;

import java.util.List;

public class SearchResult{
	private int totalResultCount;
	private String searchURL;
	private List<Item> items;

	public void setTotalResultCount(int totalResultCount) {
		this.totalResultCount = totalResultCount;
	}

	public void setSearchURL(String searchURL) {
		this.searchURL = searchURL;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

	public int getTotalResultCount(){
		return totalResultCount;
	}

	public String getSearchURL(){
		return searchURL;
	}

	public List<Item> getItems(){
		return items;
	}
}