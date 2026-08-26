package promobot.model.amazon;

public class Item {
	private String detailPageURL;
	private Images images;
	private OffersV2 offersV2;
	private String asin;
	private ItemInfo itemInfo;

	public void setDetailPageURL(String detailPageURL) {
		this.detailPageURL = detailPageURL;
	}

	public void setImages(Images images) {
		this.images = images;
	}

	public void setOffersV2(OffersV2 offersV2) {
		this.offersV2 = offersV2;
	}

	public void setAsin(String asin) {
		this.asin = asin;
	}

	public void setItemInfo(ItemInfo itemInfo) {
		this.itemInfo = itemInfo;
	}

	public String getDetailPageURL(){
		return detailPageURL;
	}

	public Images getImages(){
		return images;
	}

	public OffersV2 getOffersV2(){
		return offersV2;
	}

	public String getAsin(){
		return asin;
	}

	public ItemInfo getItemInfo(){
		return itemInfo;
	}
}
