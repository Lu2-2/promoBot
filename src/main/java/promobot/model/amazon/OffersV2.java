package promobot.model.amazon;

import java.util.List;

public class OffersV2{
	private List<Listings> listings;

	public void setListings(List<Listings> listings) {
		this.listings = listings;
	}

	public List<Listings> getListings(){
		return listings;
	}
}