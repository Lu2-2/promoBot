package promobot.model;

public class Money{
	private String displayAmount;
	private Object amount;
	private String currency;

	public void setDisplayAmount(String displayAmount) {
		this.displayAmount = displayAmount;
	}

	public void setAmount(Object amount) {
		this.amount = amount;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getDisplayAmount(){
		return displayAmount;
	}

	public Object getAmount(){
		return amount;
	}

	public String getCurrency(){
		return currency;
	}
}
