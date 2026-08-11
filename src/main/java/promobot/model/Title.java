package promobot.model;

public class Title{
	private String displayValue;
	private String label;
	private String locale;

	public void setDisplayValue(String displayValue) {
		this.displayValue = displayValue;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public void setLocale(String locale) {
		this.locale = locale;
	}

	public String getDisplayValue(){
		return displayValue;
	}

	public String getLabel(){
		return label;
	}

	public String getLocale(){
		return locale;
	}
}
